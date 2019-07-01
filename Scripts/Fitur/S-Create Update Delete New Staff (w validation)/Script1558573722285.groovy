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

CustomKeywords.'common.login.loginAdminSatker'()

WebUI.waitForPageLoad(4)

WebUI.click(findTestObject('Object Repository/Satker/Left Menu/lblDaftarStaff'))

WebUI.waitForPageLoad(4)

WebUI.click(findTestObject('Object Repository/General/iconTambah'))

rnd_idstaff = RandStr.randomNumeric(5)

WebUI.click(findTestObject('Object Repository/General/btnSubmit'))

WebUI.delay(4)

while ((WebUI.verifyElementPresent(findTestObject('Object Repository/Satker/Menu Staff/popUpAddValidation'), 4, FailureHandling.OPTIONAL)) && (WebUI.verifyElementPresent(findTestObject('Object Repository/Satker/Menu Staff/popUpAddStaff'), 4, FailureHandling.OPTIONAL))){
		if (WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/txtIdAgen'), 'value') == ''){
			WebUI.setText(findTestObject('Object Repository/Satker/Menu Staff/txtIdAgen'), id_staff + '.' + rnd_idstaff)
		}
		
		else if (WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/lstTipe'), 'value', FailureHandling.OPTIONAL) == ''){
			WebUI.click(findTestObject('Object Repository/Satker/Menu Staff/lstTipe'), FailureHandling.OPTIONAL)
			WebUI.selectOptionByLabel(findTestObject('Object Repository/Satker/Menu Staff/lstTipe'), 'PPK', true, FailureHandling.OPTIONAL)
		}
		
		else if (WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/txtTelepon'), 'value', FailureHandling.OPTIONAL) == ''){
			WebUI.setText(findTestObject('Object Repository/Satker/Menu Staff/txtTelepon'), telp_staff, FailureHandling.OPTIONAL)
		}
		
		else if (WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/txtEmail'), 'value', FailureHandling.OPTIONAL) == ''){
			WebUI.setText(findTestObject('Object Repository/Satker/Menu Staff/txtEmail'), email_staff + '.' + rnd_idstaff + '@mail.com', FailureHandling.OPTIONAL)
		}
		else if (WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/txtNama'), 'value') == ''){
			WebUI.setText(findTestObject('Object Repository/Satker/Menu Staff/txtNama'), nama_staff + rnd_idstaff)
		}
		
		else{
			check_email = WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/txtEmail'), 'value')
			if (!((check_email.contains('@') || check_email.contains('.com')) || check_email.contains('.co.id'))){
				WebUI.setText(findTestObject('Object Repository/Satker/Menu Staff/txtEmail'), email_staff + '.' + rnd_idstaff + '@mail.com')
			}
		}
		
		WebUI.click(findTestObject('Object Repository/General/btnSubmit'), FailureHandling.OPTIONAL)
	}

WebUI.waitForPageLoad(4)

WebUI.refresh()

WebUI.waitForElementVisible(findTestObject('Object Repository/General/txtSearch'), 4)

WebUI.setText(findTestObject('Object Repository/General/txtSearch'), nama_staff + rnd_idstaff)

WebUI.click(findTestObject('Object Repository/General/btnEdit'))

WebUI.delay(4)

WebUI.setText(findTestObject('Object Repository/Satker/Menu Staff/txtEditNamaAgen'), new_nama_staff + ' ' + rnd_idstaff)

WebUI.setText(findTestObject('Object Repository/Satker/Menu Staff/txtEditTeleponAgen'), new_telp_staff)

WebUI.click(findTestObject('Object Repository/General/btnUpdate'))

WebUI.waitForPageLoad(4)

WebUI.waitForElementVisible(findTestObject('Object Repository/General/txtSearch'), 4)

WebUI.setText(findTestObject('Object Repository/General/txtSearch'), new_nama_staff + ' ' + rnd_idstaff)

WebUI.click(findTestObject('Object Repository/General/btnDelete'))

WebUI.click(findTestObject('Object Repository/Satker/Menu Staff/btnOKDelete'))

WebUI.refresh()

WebUI.waitForElementVisible(findTestObject('Object Repository/General/txtSearch'), 4)

WebUI.setText(findTestObject('Object Repository/General/txtSearch'), new_nama_staff + ' ' + rnd_idstaff)

WebUI.verifyTextNotPresent((new_nama_staff + ' ' + rnd_idstaff), true)

'Logout'
CustomKeywords.'common.logout.logoutUser'()

'Tutup browser'
WebUI.closeBrowser()

