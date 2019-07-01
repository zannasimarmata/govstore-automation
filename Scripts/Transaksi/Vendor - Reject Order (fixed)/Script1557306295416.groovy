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
import com.kms.katalon.core.exception.StepErrorException as StepErrorException

WebUI.callTestCase(findTestCase('General/Open Browser'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.login.loginVendor'()

'Move to menu List Pengajuan'
WebUI.click(findTestObject('Object Repository/Vendor/Left Menu/lblListPengajuan'))

'Input text [Invoice No] in to search bar to filter order that will be processed'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify whether the Invoice number is exist'
WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Checking the status, should be \'Order Diteruskan Ke Vendor\''
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Vendor/Menu List Pengajuan/lblStatusOrder')), 'Order Diteruskan Ke Vendor', true)

'Click button Detail to see order detail'
WebUI.click(findTestObject('Object Repository/Vendor/Menu List Pengajuan/btnDetail'))

'Click button Reject to reject order'
WebUI.click(findTestObject('Object Repository/Vendor/Menu List Pengajuan/btnReject'))

'Click \'Kirim\' without reason'
WebUI.click(findTestObject('Object Repository/Vendor/Menu List Pengajuan/btnKirim'))

if (WebUI.verifyElementPresent(findTestObject('Object Repository/Vendor/Menu List Pengajuan/popUpFailedReject'), 4)){
	println 'Reason is empty'
		
	WebUI.setText(findTestObject('Object Repository/Vendor/Menu List Pengajuan/txtAlasanReject'), 'we are closed temporarily')
		
	'Click \'Kirim\' after input reason'
	WebUI.click(findTestObject('Object Repository/Vendor/Menu List Pengajuan/btnKirim'))
}
else{
	throw new StepErrorException('this is not right')
}

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

'Input text [Invoice No] in to search bar to filter order that will be processed'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Checking invoice should not exist in \'List Pengajuan\''
WebUI.verifyTextNotPresent(GlobalVariable.InvoiceNo, true)

'Move to menu \'Status Proses Pengajuan\''
WebUI.click(findTestObject('Object Repository/Vendor/Left Menu/lblStatusPengajuan'))

'Input text [Invoice No] in to search bar to filter order that will be processed'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify invoice existence'
WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Verify order status, should be \'Order Dikirim\''
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Vendor/Menu Status Proses Pengajuan/lblStatusOrder')), 'Order Ditolak Vendor', true)

'Click button Detail to see order detail'
WebUI.click(findTestObject('Object Repository/Vendor/Menu Status Proses Pengajuan/btnDetail'))

'Close detail order pop up'
WebUI.click(findTestObject('Object Repository/Vendor/Menu Status Proses Pengajuan/iconClose'))

'Logout from user Vendor'
CustomKeywords.'common.logout.logoutUser'()

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

'Close browser'
//WebUI.closeBrowser()
