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

WebUI.click(findTestObject('Object Repository/Admin/Left Menu/Unblock User'))

WebUI.verifyElementPresent(findTestObject('Object Repository/General/txtSearch'), 4)

WebUI.setText(findTestObject('Object Repository/General/txtSearch'), email)

WebUI.click(findTestObject('Object Repository/Admin/Unblock User/iconChecklist'))

WebUI.click(findTestObject('Object Repository/Admin/Unblock User/lstUbahStatus'))

WebUI.selectOptionByLabel(findTestObject('Object Repository/Admin/Unblock User/lstUbahStatus'), 'Active', true)

WebUI.click(findTestObject('Object Repository/Admin/Unblock User/btnAktifkan'))

WebUI.waitForPageLoad(4)

WebUI.waitForElementPresent(findTestObject('Object Repository/General/txtSearch'), 4)

WebUI.setText(findTestObject('Object Repository/General/txtSearch'), email)

WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Admin/Unblock User/lblStatus'), 'value'), 'Active', true)

CustomKeywords.'common.logout.logoutUser'()
