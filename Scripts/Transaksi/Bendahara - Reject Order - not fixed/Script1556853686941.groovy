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

'Login as Bendahara'
CustomKeywords.'common.login.loginBendahara'()

'Go to menu List Pengajuan'
WebUI.click(findTestObject('Object Repository/Bendahara/lblMenuListPengajuan'))

'Waiting page loaded in 2 secs'
WebUI.waitForPageLoad(2)

'Input Invoice Number in to search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify whether the Invoice is exist'
WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Verify whether the status should be \'Order Diterima\''
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Bendahara/lblStatusOrder')), 'Order Diterima', true)

'Click button Detail'
WebUI.click(findTestObject('Object Repository/Bendahara/btnDetail'))

'Click button Approve'
WebUI.click(findTestObject('Object Repository/Bendahara/btnReject'))

'Click \'Kirim\' without input reject reason'
WebUI.click(findTestObject('Object Repository/Bendahara/btnKirimReject'))

'Input rejection reason'
WebUI.setText(findTestObject('Object Repository/Bendahara/txtAlasanReject'), 'no budget')

'Click \'Kirim\' to confirm rejection'
WebUI.click(findTestObject('Object Repository/Bendahara/btnKirimReject'))

'Wait for page loaded 2 secs'
WebUI.waitForPageLoad(2)

'Input text in search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify the invoice should not exist anymore'
if(WebUI.verifyTextNotPresent(GlobalVariable.InvoiceNo, true)){
	println 'Order is rejected succesfully'
}
else{
	throw new StepErrorException('Order should not appear')
}


'Logout from user DirTeknis'
CustomKeywords.'common.logout.logoutUser'()

'Wait for page fully loaded in 2 secs'
WebUI.waitForPageLoad(2)

'Close browser'
WebUI.closeBrowser()