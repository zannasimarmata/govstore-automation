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

'Login as Dirteknis'
CustomKeywords.'common.login.loginDirTeknis'()

'Move to menu Report Order'
WebUI.click(findTestObject('Object Repository/DirTeknis/Left Menu/lblReportOrder'))

'Input Invoice Number in to search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify Invoice existence'
WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Verify whether the status already updated to be \'Order Dikirim\''
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/DirTeknis/Report Order/lblStatusOrder')), 'Order Dikirim', 
    true)

'Move to menu List Order'
WebUI.click(findTestObject('Object Repository/DirTeknis/Left Menu/lblListOrder'))

'Input Invoice Number in to search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify whether the invoice is exist'
WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Verify whether the status already updated to be \'Order Dikirim\''
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/DirTeknis/List Order/lblStatusOrder')), 'Order Dikirim', true)

'Click button \'Konfirmasi\''
WebUI.click(findTestObject('Object Repository/DirTeknis/List Order/btnKonfirmasi'))

'Click button \'Terima Barang\''
WebUI.click(findTestObject('Object Repository/DirTeknis/List Order/btnTerimaBarang'))

'Wait for page loaded 4 secs'
WebUI.waitForPageLoad(4)

'Input Invoice Number in to search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify whether the status already updated to be \'Order Diterima\''
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/DirTeknis/List Order/lblStatusOrder')), 'Order Diterima', 
    true)

'Click Detail button'
WebUI.click(findTestObject('Object Repository/DirTeknis/List Order/btnDetailOrder'))

'Close pop up detail order'
WebUI.click(findTestObject('Object Repository/DirTeknis/List Order/iconClose'))

'Move to menu Report Order'
WebUI.click(findTestObject('Object Repository/DirTeknis/Left Menu/lblReportOrder'))

'Input Invoice Number in to search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify whether the Invoice is exist'
WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Verify whether the status already updated to be \'Order Diterima\''
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/DirTeknis/Report Order/lblStatusOrder')), 'Order Diterima', 
    true)

'Click Detail'
WebUI.click(findTestObject('Object Repository/DirTeknis/List Order/btnDetailOrder'))

'Close pop up'
WebUI.click(findTestObject('Object Repository/DirTeknis/List Order/iconClose'))

'Logout from user DirTeknis'
CustomKeywords.'common.logout.logoutUser'()

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

