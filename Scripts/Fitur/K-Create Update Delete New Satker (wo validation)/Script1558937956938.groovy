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

CustomKeywords.'common.login.loginKPPN'()

WebUI.verifyElementVisible(findTestObject('Admin/Left Menu/Satker'))

WebUI.click(findTestObject('Admin/Left Menu/Satker'))

WebUI.verifyElementPresent(findTestObject('General/iconTambah'), 3)

WebUI.click(findTestObject('General/iconTambah'))

WebUI.delay(2)

rnd_idsatker = RandStr.randomNumeric(3)

WebUI.click(findTestObject('KPPN/Menu Satker/btnGetLocation'))

WebUI.setText(findTestObject('KPPN/Menu Satker/txtKodeSatker'), (IdSatker + '-') + rnd_idsatker)

WebUI.setText(findTestObject('KPPN/Menu Satker/txtNamaSatker'), NamaSatker + rnd_idsatker)

WebUI.setText(findTestObject('KPPN/Menu Satker/txtEmailSatker'), EmailSatker)

WebUI.setText(findTestObject('KPPN/Menu Satker/txtAlamatSatker'), AlamatSatker)

WebUI.delay(4)

WebUI.click(findTestObject('KPPN/Menu Satker/btnTambahSatker'))

WebUI.waitForPageLoad(4)

WebUI.refresh()

WebUI.verifyElementVisible(findTestObject('General/txtSearch'))

WebUI.setText(findTestObject('General/txtSearch'), NamaSatker + rnd_idsatker)

WebUI.verifyElementPresent(findTestObject('General/btnEdit'), 2)

WebUI.click(findTestObject('General/btnEdit'))

WebUI.verifyElementVisible(findTestObject('KPPN/Menu Satker/lblTitleEditSatker'))

WebUI.verifyElementPresent(findTestObject('KPPN/Menu Satker/txtEditNamaSatker'), 2)

WebUI.setText(findTestObject('KPPN/Menu Satker/txtEditKodeSatker'), IdSatker + '-' + rnd_idsatker)

WebUI.setText(findTestObject('KPPN/Menu Satker/txtEditNamaSatker'), new_NamaSatker + rnd_idsatker)

WebUI.setText(findTestObject('KPPN/Menu Satker/txtEditAlamatSatker'), new_AlamatSatker + rnd_idsatker)

WebUI.click(findTestObject('General/btnUpdate'))

WebUI.waitForElementPresent(findTestObject('General/txtSearch'), 4)

WebUI.refresh()

WebUI.setText(findTestObject('General/txtSearch'), new_NamaSatker + rnd_idsatker)

WebUI.verifyTextPresent(new_NamaSatker + rnd_idsatker, false)

WebUI.verifyElementPresent(findTestObject('General/btnDelete'), 3)

WebUI.click(findTestObject('General/btnDelete'))

WebUI.click(findTestObject('Object Repository/General/btnDeleteOK'))

WebUI.waitForPageLoad(4)

WebUI.refresh()

WebUI.verifyElementPresent(findTestObject('General/txtSearch'), 4)

WebUI.setText(findTestObject('General/txtSearch'), new_NamaSatker + rnd_idsatker)

WebUI.verifyTextNotPresent(new_NamaSatker + rnd_idsatker, false)

CustomKeywords.'common.logout.logoutUser'()

WebUI.closeBrowser()

