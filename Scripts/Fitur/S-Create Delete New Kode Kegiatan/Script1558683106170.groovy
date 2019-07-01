import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('General/Open Browser'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.login.loginAdminSatker'()

WebUI.click(findTestObject('Object Repository/Satker/Left Menu/Kode Kegiatan'))
WebUI.delay(4)

WebUI.waitForElementPresent(findTestObject('Object Repository/General/iconTambah'), 4)

WebUI.click(findTestObject('Object Repository/General/iconTambah'))

WebUI.click(findTestObject('Object Repository/General/btnSubmit'))

while(WebUI.verifyElementPresent(findTestObject('Object Repository/Satker/Kode Kegiatan/popUpAddFailed'), 4, FailureHandling.OPTIONAL)){
	if (WebUI.getAttribute(findTestObject('Object Repository/Satker/Kode Kegiatan/txtKodeKegiatan'), 'value') == ''){
		WebUI.setText(findTestObject('Object Repository/Satker/Kode Kegiatan/txtKodeKegiatan'), kodeKegiatan)
	}
	else if(WebUI.getAttribute(findTestObject('Object Repository/Satker/Kode Kegiatan/txtDeskripsi'), 'value') == ''){
		WebUI.setText(findTestObject('Object Repository/Satker/Kode Kegiatan/txtDeskripsi'), deskripsi, FailureHandling.OPTIONAL)
	}
	else{
		println 'good to go!'
	}
	WebUI.click(findTestObject('Object Repository/General/btnSubmit'), FailureHandling.OPTIONAL)
}

WebUI.waitForPageLoad(4)

WebUI.refresh()

WebUI.setText(findTestObject('Object Repository/General/txtSearch'), kodeKegiatan)

WebUI.click(findTestObject('Object Repository/General/btnDelete'))

WebUI.click(findTestObject('Object Repository/General/btnDeleteOK'))

WebUI.waitForPageLoad(4)

WebUI.refresh()

WebUI.setText(findTestObject('Object Repository/General/txtSearch'), kodeKegiatan)

WebUI.verifyTextNotPresent(kodeKegiatan, true)

CustomKeywords.'common.logout.logoutUser'()

WebUI.closeBrowser()


