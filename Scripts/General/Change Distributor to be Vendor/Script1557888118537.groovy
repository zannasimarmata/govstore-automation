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

CustomKeywords.'common.login.loginAdminSatker'()

WebUI.verifyTextNotPresent('Distributor', false)

WebUI.click(findTestObject('Object Repository/Satker/Left Menu/Category'))

WebUI.verifyTextNotPresent('Distributor', false)

WebUI.click(findTestObject('Object Repository/Satker/Menu Category/iconTambahCategory'))

WebUI.verifyTextNotPresent('Distributor', false)

WebUI.click(findTestObject('Object Repository/Satker/Menu Category/iconClose'))

WebUI.click(findTestObject('Object Repository/Satker/Left Menu/lblDaftarStaff'))

WebUI.verifyTextNotPresent('Distributor', false)

WebUI.click(findTestObject('Object Repository/General/iconTambah'))

WebUI.verifyTextNotPresent('Distributor', false)

WebUI.click(findTestObject('Object Repository/Satker/Menu Category/iconClose'))

WebUI.click(findTestObject('Object Repository/Satker/Left Menu/lblDaftarVendor'))

WebUI.verifyTextNotPresent('Distributor', false)

WebUI.click(findTestObject('Object Repository/General/iconTambah'))

WebUI.click(findTestObject('Object Repository/Satker/Menu Category/iconClose'))

WebUI.verifyTextNotPresent('Distributor', false)

WebUI.click(findTestObject('Object Repository/General/btnEdit'))

WebUI.verifyTextNotPresent('Distributor', false)

WebUI.click(findTestObject('Object Repository/Satker/Menu Vendor/iconCloseEdit'))

CustomKeywords.'common.logout.logoutUser'()

WebUI.waitForPageLoad(4)

CustomKeywords.'common.login.loginDirTeknis'()

WebUI.verifyTextNotPresent('Distributor', false)

WebUI.click(findTestObject('Object Repository/DirTeknis/Left Menu/lblCatalog'))

WebUI.verifyTextNotPresent('Distributor', false)

CustomKeywords.'common.logout.logoutUser'()

WebUI.closeBrowser()