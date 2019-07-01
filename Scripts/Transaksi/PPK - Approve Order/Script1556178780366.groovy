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

import org.eclipse.persistence.internal.oxm.record.json.JSONParser.value_return
import org.openqa.selenium.Keys as Keys

//WebUI.callTestCase(findTestCase('General/Open Browser'), [:], FailureHandling.STOP_ON_FAILURE)

'Login as PPK'
CustomKeywords.'common.login.loginPPK'()

'Move to menu \'List Pengajuan\''
WebUI.click(findTestObject('Object Repository/PPK/Left Menu/lblListPengajuan'))

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

'Input Invoice Number in to search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Mengecek status order baru harus = Order Masuk'
WebUI.verifyMatch(WebUI.getText(findTestObject('PPK/List Pengajuan/lblStatusOrder')), 'Order Masuk', true)

WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Click button Detail'
WebUI.click(findTestObject('Object Repository/PPK/List Pengajuan/btnDetail'))

WebUI.delay(4)

'Klik button Approve'
WebUI.click(findTestObject('Object Repository/PPK/List Pengajuan/btnApprove'))

'Wait for page fully loaded in 4 secs'
//WebUI.waitForPageLoad(4)

WebUI.verifyElementPresent(findTestObject('Object Repository/General/txtSearch'), 4)

'Input Invoice Number in to search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Validate and Check, the invoice should not exist anymore in menu \'List Pengajuan\''
WebUI.verifyTextNotPresent(GlobalVariable.InvoiceNo, true)

'Move to menu Status Proses Pengajuan'
WebUI.click(findTestObject('Object Repository/PPK/Left Menu/lblStatusProsesPengajuan'))

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

'Input Invoice Number in to search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Check the Invoice No should be exist'
WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Verify Invoice status, should be \'Order Diteruskan Ke Pengadaan\''
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/PPK/Status Proses Pengajuan/lblStatusPengajuan')), 'Order Diteruskan Ke Pengadaan', true)

'Click button Detail'
WebUI.click(findTestObject('Object Repository/PPK/Status Proses Pengajuan/btnDetail'))

'Click Close'
WebUI.click(findTestObject('Object Repository/PPK/Status Proses Pengajuan/iconClose'))

'Logout from user PPK'
CustomKeywords.'common.logout.logoutUser'()

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

