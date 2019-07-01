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
WebUI.click(findTestObject('Object Repository/Vendor/Left Menu/lblStatusNego'))

'Input text [Invoice No] in to search bar to filter order that will be processed'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify whether the Invoice number is exist'
WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Click button Status Nego to see items'
WebUI.click(findTestObject('Object Repository/Vendor/Menu Status Nego/btnStatusNego'))

'Get base price'
base_price_idr = WebUI.getText(findTestObject('Object Repository/Vendor/Menu Status Nego/lblPriceItemNego'))
base_price = base_price_idr.replace('Rp ', '')
fix_base_price = base_price.replace('.', '')
println (fix_base_price)
println (Integer.parseInt(fix_base_price)-100).toString()

'Click button Nego to see nego from buyer'
WebUI.click (findTestObject('Object Repository/Vendor/Menu Status Nego/btnNegoItem'))

'Input price from vendor'
WebUI.setText(findTestObject('Object Repository/Vendor/Menu Status Nego/txtHargaNego'), vendor_price_nego)

'Get price nego inputted by buyer'
buyer_price_nego = WebUI.getText(findTestObject('Object Repository/Vendor/Menu Status Nego/lblNegoPricefrBuyer'))
price_nego = buyer_price_nego.replace('Rp ', '')
b_price_nego = price_nego.replace('.', '')
println(b_price_nego)

'Click Nego to confirm nego price'
WebUI.click(findTestObject('Object Repository/Vendor/Menu Status Nego/btnConfirmNego'))

while (WebUI.verifyElementPresent(findTestObject('Object Repository/Vendor/Menu Status Nego/popUpNegoFailed'), 4)){
	
		WebUI.waitForPageLoad(3)
		
		'Input text [Invoice No] in to search bar to filter order that will be processed'
		WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)
	
		'Verify whether the Invoice number is exist'
		WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)
	
		'Click button Status Nego to see nego from buyer'
		WebUI.click(findTestObject('Object Repository/Vendor/Menu Status Nego/btnStatusNego'))
		
		'Click button Nego to see nego from buyer'
		WebUI.click (findTestObject('Object Repository/Vendor/Menu Status Nego/btnNegoItem'))
	
		'Input price from vendor'
		WebUI.setText(findTestObject('Object Repository/Vendor/Menu Status Nego/txtHargaNego'), (Integer.parseInt(fix_base_price)-100).toString())
		
		'Click Nego to confirm nego price'
		WebUI.click(findTestObject('Object Repository/Vendor/Menu Status Nego/btnConfirmNego'))
}

if (WebUI.verifyElementPresent(findTestObject('Object Repository/Vendor/Menu Status Nego/popUpNegoBerhasil'), 4)){
	if (Integer.parseInt(vendor_price_nego) < Integer.parseInt(b_price_nego) || Integer.parseInt(vendor_price_nego) > Integer.parseInt(base_price)){
		throw new StepErrorException('this shall not pass')
	}
	else{
		println 'good to go'
	}
}







/*

if (((Integer.parseInt(vendor_price_nego) < Integer.parseInt(b_price_nego)) && (WebUI.verifyElementPresent(findTestObject('Object Repository/Vendor/Menu Status Nego/popUpNegoFailed'), 2))) || ((Integer.parseInt(vendor_price_nego) > Integer.parseInt(base_price)) && (WebUI.verifyElementPresent(findTestObject('Object Repository/Vendor/Menu Status Nego/popUpNegoFailed'), 2))) ==  true){
		WebUI.waitForPageLoad(3)
		'Input text [Invoice No] in to search bar to filter order that will be processed'
		WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)
	
		'Verify whether the Invoice number is exist'
		WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)
	
		'Click button Status Nego to see nego from buyer'
		WebUI.click(findTestObject('Object Repository/Vendor/Menu Status Nego/btnStatusNego'))
		
		'Click button Nego to see nego from buyer'
		WebUI.click (findTestObject('Object Repository/Vendor/Menu Status Nego/btnNegoItem'))
	
		'Input price from vendor'
		WebUI.setText(findTestObject('Object Repository/Vendor/Menu Status Nego/txtHargaNego'), (Integer.parseInt(fix_base_price)-100).toString())
	
		'Click Nego to confirm nego price'
		WebUI.click(findTestObject('Object Repository/Vendor/Menu Status Nego/btnConfirmNego'))
	}
else if (((Integer.parseInt(vendor_price_nego) < Integer.parseInt(b_price_nego)) || (Integer.parseInt(vendor_price_nego) > Integer.parseInt(
    base_price))) && WebUI.verifyElementPresent(findTestObject('Object Repository/Vendor/Menu Status Nego/popUpNegoBerhasil'), 
    2)) {
    throw new StepErrorException('Price can not be less than base price or buyer price')
} 
else {
    println('continue')
}

*/


'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

'Logout from user Vendor'
CustomKeywords.'common.logout.logoutUser'()

'Login as Pengadaan to approve order'
//WebUI.callTestCase(findTestCase('Transaksi/Pengadaan - Approve Order'), [:], FailureHandling.STOP_ON_FAILURE)

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

'Close browser'

