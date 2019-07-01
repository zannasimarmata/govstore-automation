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
import org.apache.commons.lang.RandomStringUtils as RandStr

WebUI.callTestCase(findTestCase('General/Open Browser'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.login.loginKPPN'()

WebUI.verifyElementVisible(findTestObject('Admin/Left Menu/Satker'))

WebUI.click(findTestObject('Admin/Left Menu/Satker'))

WebUI.verifyElementPresent(findTestObject('General/iconTambah'), 3)

WebUI.click(findTestObject('General/iconTambah'))

WebUI.delay(2)

rnd_idsatker = RandStr.randomNumeric(3)

WebUI.click(findTestObject('KPPN/Menu Satker/btnGetLocation'))

WebUI.delay(4)

WebUI.click(findTestObject('KPPN/Menu Satker/btnTambahSatker'))


while ((WebUI.verifyElementPresent(findTestObject('Object Repository/KPPN/Menu Satker/popupAddFailed'), 4, FailureHandling.OPTIONAL)) && (WebUI.verifyElementVisible(findTestObject('Object Repository/KPPN/Menu Satker/popUpAddSatker'), FailureHandling.OPTIONAL))){
	println 'Failed to add'
	
	if (WebUI.getAttribute(findTestObject('KPPN/Menu Satker/txtKodeSatker'), 'value') == '') {
		println('Id Satker kosong')
		
		WebUI.setText(findTestObject('KPPN/Menu Satker/txtKodeSatker'), (IdSatker + '-') + rnd_idsatker)
	}
	else if (WebUI.getAttribute(findTestObject('KPPN/Menu Satker/txtNamaSatker'), 'value') == '') {
		println('Nama Satker kosong')
		
		WebUI.setText(findTestObject('KPPN/Menu Satker/txtNamaSatker'), NamaSatker + rnd_idsatker)
	}
	else if (WebUI.getAttribute(findTestObject('KPPN/Menu Satker/txtAlamatSatker'), 'value') == '') {
		println('Alamat Satker kosong')
		
		WebUI.setText(findTestObject('KPPN/Menu Satker/txtAlamatSatker'), AlamatSatker)	
	}
	else if (WebUI.getAttribute(findTestObject('KPPN/Menu Satker/txtEmailSatker'), 'value') == '') {
		println('Email Satker kosong')
		
		WebUI.setText(findTestObject('KPPN/Menu Satker/txtEmailSatker'), rnd_idsatker)
	}
	else if (WebUI.getAttribute(findTestObject('KPPN/Menu Satker/txtGeoLocation'), 'value') == ''){
		println('Geo Location kosong')
		
		WebUI.click(findTestObject('KPPN/Menu Satker/btnGetLocation'))
	}
	else if (WebUI.getAttribute(findTestObject('Object Repository/KPPN/Menu Satker/txtTelp'), 'value') == ''){
		println('Telp kosong')
		
		WebUI.setText(findTestObject('Object Repository/KPPN/Menu Satker/txtTelp'), telp)
	}
	else if (!((rnd_idsatker.contains('@') || rnd_idsatker.contains('.com')) || rnd_idsatker.contains('.co.id'))) {
		println('Format Email salah')
		
		WebUI.setText(findTestObject('KPPN/Menu Satker/txtEmailSatker'), (rnd_idsatker + '.') + EmailSatker + '@mail.com')
	}
	WebUI.click(findTestObject('KPPN/Menu Satker/btnTambahSatker'))
}

WebUI.waitForPageLoad(4)

WebUI.refresh()

WebUI.verifyElementVisible(findTestObject('General/txtSearch'))

WebUI.delay(2)

WebUI.setText(findTestObject('General/txtSearch'), NamaSatker + rnd_idsatker)

WebUI.verifyElementPresent(findTestObject('General/btnEdit'), 2)

WebUI.click(findTestObject('General/btnEdit'))

WebUI.delay(2, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('KPPN/Menu Satker/lblTitleEditSatker'))

WebUI.verifyElementPresent(findTestObject('KPPN/Menu Satker/txtEditNamaSatker'), 2)

WebUI.clearText(findTestObject('KPPN/Menu Satker/txtEditNamaSatker'))

WebUI.setText(findTestObject('KPPN/Menu Satker/txtEditNamaSatker'), new_NamaSatker + rnd_idsatker)

WebUI.verifyElementPresent(findTestObject('KPPN/Menu Satker/txtEditAlamatSatker'), 2)

WebUI.clearText(findTestObject('KPPN/Menu Satker/txtEditAlamatSatker'))

WebUI.setText(findTestObject('KPPN/Menu Satker/txtEditAlamatSatker'), new_AlamatSatker + rnd_idsatker)

WebUI.verifyElementPresent(findTestObject('KPPN/Menu Satker/txtEditGeoLocation'), 2)

WebUI.delay(2)

WebUI.click(findTestObject('General/btnUpdate'))

WebUI.waitForElementPresent(findTestObject('General/txtSearch'), 4)

WebUI.refresh()

WebUI.setText(findTestObject('General/txtSearch'), new_NamaSatker + rnd_idsatker)

WebUI.verifyTextPresent(new_NamaSatker + rnd_idsatker, false)

WebUI.verifyElementPresent(findTestObject('General/btnDelete'), 3)

WebUI.click(findTestObject('General/btnDelete'))

WebUI.click(findTestObject('Object Repository/General/btnDeleteOK'))

WebUI.waitForPageLoad(4)

WebUI.refresh()

WebUI.verifyElementPresent(findTestObject('General/txtSearch'), 4)

WebUI.setText(findTestObject('General/txtSearch'), new_NamaSatker + rnd_idsatker)

WebUI.verifyTextNotPresent(new_NamaSatker + rnd_idsatker, false)

CustomKeywords.'common.logout.logoutUser'()

WebUI.closeBrowser()