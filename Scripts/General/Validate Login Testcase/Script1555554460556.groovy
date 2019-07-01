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

WebUI.clearText(findTestObject('Admin/Login Page/txtUsername'))

WebUI.clearText(findTestObject('Admin/Login Page/txtPassword'))

WebUI.click(findTestObject('Admin/Login Page/btnMasuk'))

if (WebUI.verifyElementPresent(findTestObject('Admin/Login Page/popUpLoginFailed'), 4) == true) {
	println('Validation username & Password OK')
	
	WebUI.setText(findTestObject('Object Repository/Admin/Login Page/txtUsername'), 'Aladin')
	WebUI.click(findTestObject('Admin/Login Page/btnMasuk'))
		
	if (WebUI.verifyElementPresent(findTestObject('Admin/Login Page/popUpValidasiPass'), 4) == true){
		println('Validation Password OK')
		
		WebUI.setText(findTestObject('Object Repository/Admin/Login Page/txtPassword'), 'password')
		WebUI.clearText(findTestObject('Object Repository/Admin/Login Page/txtUsername'))
		WebUI.click(findTestObject('Object Repository/Admin/Login Page/txtUsername'))
		WebUI.click(findTestObject('Admin/Login Page/btnMasuk'))
		
		if (WebUI.verifyElementPresent(findTestObject('Admin/Login Page/popUpInvalidUsername'), 4) == true) {
			println('Validation Username OK')
			
			WebUI.setText(findTestObject('Object Repository/Admin/Login Page/txtUsername'), 'Aladin')
			WebUI.click(findTestObject('Admin/Login Page/btnMasuk'))
			
			if ((WebUI.verifyElementPresent(findTestObject('Admin/Login Page/popUpInvalidUsername'), 4) == true)) {
				println('Validation Username and Password not match or not Exist OK')
				
				/*
				WebUI.setText(findTestObject('Object Repository/Admin/Login Page/txtUsername'), GlobalVariable.UsrSatker)
				WebUI.setText(findTestObject('Object Repository/Admin/Login Page/txtPassword'), GlobalVariable.pswdSatker)
				WebUI.click(findTestObject('Admin/Login Page/btnMasuk'))
				*/
				CustomKeywords.'common.login.loginAdmin'()
				//WebUI.waitForPageLoad(4)
				WebUI.verifyElementPresent(findTestObject('Object Repository/Admin/Menu Dashboard/lstLogout'), 4)
			}
		}
	}
}

else {
	WebUI.closeBrowser()
}

WebUI.closeBrowser()

