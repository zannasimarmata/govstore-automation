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

CustomKeywords.'common.login.loginAdmin'()

WebUI.click(findTestObject('Object Repository/Admin/Left Menu/List PKN'))

WebUI.waitForPageLoad(4)

WebUI.click(findTestObject('Object Repository/General/iconTambah'))

WebUI.click(findTestObject('Object Repository/General/btnTambah'))

while ((WebUI.verifyElementPresent(findTestObject('Object Repository/Admin/List PKN/popUpAddFailed'), 4, FailureHandling.OPTIONAL)) && (WebUI.verifyElementVisible(findTestObject('Object Repository/Admin/List PKN/popUpAddNew'), FailureHandling.OPTIONAL))){
	if(WebUI.getAttribute(findTestObject('Object Repository/Admin/List PKN/txtUsername'), 'value', FailureHandling.OPTIONAL) == ''){
		WebUI.setText(findTestObject('Object Repository/Admin/List PKN/txtUsername'), username)
	}
	else if (WebUI.getAttribute(findTestObject('Admin/List PKN/txtNIP'), 'value') == ''){
		WebUI.setText(findTestObject('Admin/List PKN/txtNIP'), nip, FailureHandling.OPTIONAL)
	}
	else if (WebUI.getAttribute(findTestObject('Object Repository/Admin/List PKN/txtNIK'), 'value') == ''){
		WebUI.setText(findTestObject('Admin/List PKN/txtNIK'), nik)
	}
	else if (WebUI.getAttribute(findTestObject('Object Repository/Admin/List PKN/txtEmail'), 'value') == ''){
		WebUI.setText(findTestObject('Object Repository/Admin/List PKN/txtEmail'), email)
	}
	else if (WebUI.getAttribute(findTestObject('Object Repository/Admin/List PKN/txtNoHP'), 'value') == ''){
		WebUI.setText(findTestObject('Object Repository/Admin/List PKN/txtNoHP'), nohp)
	}
	else if (WebUI.getAttribute(findTestObject('Object Repository/Admin/List PKN/txtUploadSuratKPA'), 'value') == ''){
		def uploadKPA = CustomKeywords.'common.folderPath.uploadFile'()
		
		WebUI.uploadFile(findTestObject('Object Repository/Admin/List PKN/txtUploadSuratKPA'), uploadKPA)
	}
	WebUI.click(findTestObject('Object Repository/General/btnTambah'), FailureHandling.OPTIONAL)
}

WebUI.refresh()

WebUI.waitForElementPresent(findTestObject('Object Repository/General/txtSearch'), 4)

WebUI.setText(findTestObject('Object Repository/General/txtSearch'), username)

WebUI.click(findTestObject('Object Repository/General/btnDelete'))

WebUI.click(findTestObject('Object Repository/General/btnDeleteOK'))

WebUI.refresh()

WebUI.waitForElementPresent(findTestObject('Object Repository/General/txtSearch'), 4)

WebUI.setText(findTestObject('Object Repository/General/txtSearch'), username)

WebUI.verifyTextNotPresent(username, true)

CustomKeywords.'common.logout.logoutUser'()

WebUI.closeBrowser()