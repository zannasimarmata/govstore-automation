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

CustomKeywords.'common.login.loginBendahara'()

WebUI.click(findTestObject('Object Repository/Admin/Menu Dashboard/lstLogout'))

WebUI.click(findTestObject('Object Repository/Admin/Menu Dashboard/lblChangePassword'))

WebUI.setText(findTestObject('Object Repository/Admin/Change Password/txtNewPassword'), 'password')

//WebUI.click(findTestObject('Object Repository/Admin/Change Password/iconEye'))

WebUI.click(findTestObject('Object Repository/Admin/Change Password/btnUbahPassword'))
//while (WebUI.verifyElementVisible(findTestObject('Object Repository/Admin/Change Password/popUpFailed') == true)){
	
	WebUI.clearText(findTestObject('Object Repository/Admin/Change Password/txtNewPassword'))
	
	String newpswd = RandStr.randomAlphanumeric(6)
	
	WebUI.setText(findTestObject('Object Repository/Admin/Change Password/txtNewPassword'), newpswd)
	
	WebUI.click(findTestObject('Object Repository/Admin/Change Password/btnUbahPassword'))
//}

WebUI.refresh()

WebUI.click(findTestObject('Object Repository/Admin/Menu Dashboard/lstLogout'))

WebUI.click(findTestObject('Object Repository/Admin/Menu Dashboard/lbllogout'))

WebUI.setText(findTestObject('Object Repository/Admin/Login Page/txtUsername'), GlobalVariable.UsrBendahara)

WebUI.setText(findTestObject('Object Repository/Admin/Login Page/txtPassword'), newpswd)

WebUI.click(findTestObject('Object Repository/Admin/Login Page/btnMasuk'))

WebUI.click(findTestObject('Object Repository/Admin/Menu Dashboard/lstLogout'))

WebUI.click(findTestObject('Object Repository/Admin/Menu Dashboard/lbllogout'))

WebUI.refresh()