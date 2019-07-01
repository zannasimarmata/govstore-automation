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

//WebUI.callTestCase(findTestCase('General/Open Browser'), [:], FailureHandling.STOP_ON_FAILURE)

'Login as User Pengadaan'
CustomKeywords.'common.login.loginPengadaan'()

'Masuk ke menu List Pengadaan'
WebUI.click(findTestObject('Object Repository/Pengadaan/Left Menu/lblListPengajuan'))

'Input Invoice Number in to search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify the invoice should exist'
WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Verify order status should be \'Order Diteruskan Ke Pengadaan\''
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Pengadaan/List Pengajuan/lblStatusOrder')), 'Order Diteruskan Ke Pengadaan', true)

'Click button Detail to see Order details'
WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnDetailOrder'))

'Verify if Detail pop up appear'
WebUI.verifyElementPresent(findTestObject('Object Repository/Pengadaan/List Pengajuan/popUpDetailOrder'), 4)

'Click button Approve to process order'
WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnRejectOrder'))

'Input reason for rejecting order'
WebUI.setText(findTestObject('Object Repository/Pengadaan/List Pengajuan/txtAlasanReject'), 'Meeting is cancelled')

'Click button Kirim to confirm order rejection'
WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnKirimReject'))

'Input Invoice Number in to search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify Invoice No is not exist'
WebUI.verifyTextNotPresent(GlobalVariable.InvoiceNo, true)

'Move to menu Status Proses Pengajuan'
WebUI.click(findTestObject('Object Repository/Pengadaan/Left Menu/lblStatusProsesPengajuan'))

'Input text in search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify if Invoice No is exist'
WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Verify the status, should be \'Order Ditolak Pengadaan\''
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Pengadaan/Status Proses Pengajuan/lblStatusOrder')), 'Order Ditolak Pengadaan', true)

'Click Detail'
WebUI.click(findTestObject('Object Repository/Pengadaan/Status Proses Pengajuan/btnDetail'))

'Click close'
WebUI.click(findTestObject('Object Repository/PPK/Status Proses Pengajuan/iconClose'))

'Logout from user Pengadaan'
CustomKeywords.'common.logout.logoutUser'()

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

