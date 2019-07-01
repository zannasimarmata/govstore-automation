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

CustomKeywords.'common.login.loginVendor'()

WebUI.click(findTestObject('Vendor/Left Menu/lblEmployee'))

WebUI.verifyElementPresent(findTestObject('General/iconTambah'), 2)

WebUI.click(findTestObject('General/iconTambah'))

WebUI.delay(2)

rnd_nama = RandStr.randomNumeric(2)

WebUI.setText(findTestObject('Vendor/Menu Employee/txtNamaEmp'), (nama_employee + '-') + rnd_nama)

WebUI.setText(findTestObject('Vendor/Menu Employee/txtEmailEmp'), (nama_employee + rnd_nama) + '@gmail.com')

WebUI.setText(findTestObject('Vendor/Menu Employee/txtNoTelp'), no_telp)

WebUI.click(findTestObject('Vendor/Menu Employee/lstTypeEmp'))

WebUI.selectOptionByValue(findTestObject('Vendor/Menu Employee/lstTypeEmp'), 'kurir', false)

WebUI.click(findTestObject('General/btnSubmit'))

WebUI.waitForPageLoad(4)

WebUI.refresh()

/*
 * Edit Employee */

WebUI.setText(findTestObject('General/txtSearch'), (nama_employee + '-') + rnd_nama)

WebUI.delay(4)

WebUI.verifyElementPresent(findTestObject('General/btnEdit'), 2)

WebUI.click(findTestObject('General/btnEdit'))

WebUI.verifyElementPresent(findTestObject('Vendor/Menu Employee/txtEditNamaEmp'), 2)

WebUI.setText(findTestObject('Vendor/Menu Employee/txtEditNamaEmp'), (new_nama_employee + '-') + rnd_nama)

WebUI.setText(findTestObject('Vendor/Menu Employee/txtEditNoTelp'), new_no_telp)

//WebUI.setText(findTestObject('Vendor/Menu Employee/txtEditType'), 'Staff')

WebUI.delay(2)

WebUI.click(findTestObject('General/btnUpdate'))

WebUI.waitForPageLoad(4)

WebUI.refresh()

/* 
 * End of Edit Employee */

/*
 * Delete Employee */

WebUI.verifyElementPresent(findTestObject('General/txtSearch'), 4)

WebUI.setText(findTestObject('General/txtSearch'), (new_nama_employee + '-') + rnd_nama)

WebUI.verifyTextPresent((new_nama_employee + '-') + rnd_nama, true)

WebUI.verifyTextPresent(new_no_telp, true)

WebUI.click(findTestObject('General/btnDelete'))

WebUI.click(findTestObject('Object Repository/General/btnDeleteOK'))

WebUI.verifyElementPresent(findTestObject('General/txtSearch'), 4)

/*
 * End of Delete Employee */

WebUI.verifyElementPresent(findTestObject('General/txtSearch'), 4)

WebUI.setText(findTestObject('General/txtSearch'), (new_nama_employee + '-') + rnd_nama)

WebUI.verifyTextNotPresent((new_nama_employee + '-') + rnd_nama, true)

CustomKeywords.'common.logout.logoutUser'()

WebUI.closeBrowser()

