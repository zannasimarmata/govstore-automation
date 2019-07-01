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

//WebUI.callTestCase(findTestCase('General/Open Browser'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.login.loginVendor'()

'Move to menu List Pengajuan'
WebUI.click(findTestObject('Object Repository/Vendor/Left Menu/lblStatusNego'))

'Input text [Invoice No] in to search bar to filter order that will be processed'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify whether the Invoice number is exist'
WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Click button Status Nego to see items'
WebUI.click(findTestObject('Object Repository/Vendor/Menu Status Nego/btnStatusNego'))

'Click button Nego to see nego from buyer'
WebUI.click (findTestObject('Object Repository/Vendor/Menu Status Nego/btnNegoItem'))

WebUI.click(findTestObject('Object Repository/Vendor/Menu Status Nego/btnSetujuiHargaNego'))

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

WebUI.verifyElementPresent(findTestObject('Object Repository/General/txtSearch'), 2)

'Input text [Invoice No] in to search bar to filter order that will be processed'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify whether the Invoice number is exist'
WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Click button Status Nego to see nego from buyer'
WebUI.click(findTestObject('Object Repository/Vendor/Menu Status Nego/btnStatusNego'))

'Click button Nego to see nego from buyer'
WebUI.click (findTestObject('Object Repository/Vendor/Menu Status Nego/btnNegoItem'))

'Close pop up nego'
WebUI.click(findTestObject('Object Repository/Vendor/Menu Status Nego/iconCloseNego'))

'Logout from user Vendor'
CustomKeywords.'common.logout.logoutUser'()

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

'Close browser'
//WebUI.closeBrowser()
