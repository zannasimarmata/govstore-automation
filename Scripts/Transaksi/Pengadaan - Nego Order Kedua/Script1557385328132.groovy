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

'Login as User Pengadaan'
CustomKeywords.'common.login.loginPengadaan'()

'Masuk ke menu List Pengadaan'
WebUI.click(findTestObject('Object Repository/Pengadaan/Left Menu/lblListPengajuan'))

'Input Invoice Number in to search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify the invoice should exist'
WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Verify order status should be \'Order Diteruskan Ke Pengadaan\''
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Pengadaan/List Pengajuan/lblStatusOrder')), 'Order Diteruskan Ke Pengadaan',
	true)

'Click button Nego to continue negotiation'
WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnNegoOrder'))

'Click button Nego in desired item'
WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnNegoItem'))

'Get buyer first nego price item'
buyer_first_price = WebUI.getText(findTestObject('Object Repository/Pengadaan/List Pengajuan/lblBuyerFirstPriceNego'))
println(buyer_first_price)
first_price = buyer_first_price.replace('Rp ', '')
b_first_price = first_price.replace('.', '')
println (Integer.parseInt(b_first_price)+100) //(b_first_price)

'Get vendor nego price'
vendor_price_nego= WebUI.getText(findTestObject('Object Repository/Pengadaan/List Pengajuan/lblNegoPricefrVendor'))
price_nego = vendor_price_nego.replace('Rp ', '')
v_price_nego = price_nego.replace('.', '')
println (v_price_nego)
//println (Integer.parseInt(v_price_nego)+1000)


'Input new nego price'
WebUI.setText(findTestObject('Object Repository/Pengadaan/List Pengajuan/txtHargaNego'), buyer_new_nego)

'Click Nego'
WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnConfirmNego'))

if (Integer.parseInt(buyer_new_nego) > Integer.parseInt(v_price_nego)){
	if(WebUI.verifyElementPresent(findTestObject('Object Repository/Pengadaan/List Pengajuan/popUpNegoFailed'), 2)){
		
		WebUI.waitForPageLoad(3)
		
		'Input Invoice Number in to search bar'
		WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)
		
		'Verify the invoice should exist'
		WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)
		
		'Verify order status should be \'Order Diteruskan Ke Pengadaan\''
		WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Pengadaan/List Pengajuan/lblStatusOrder')), 'Order Diteruskan Ke Pengadaan',
			true)
		
		'Click button Nego to continue negotiation'
		WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnNegoOrder'))
		
		'Click button Nego in desired item'
		WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnNegoItem'))
		
		'Input new nego price'
		WebUI.setText(findTestObject('Object Repository/Pengadaan/List Pengajuan/txtHargaNego'), (Integer.parseInt(b_first_price)+100).toString())
		
		'Click Nego'
		WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnConfirmNego'))
	}
	else if(WebUI.verifyElementPresent(findTestObject('Object Repository/Pengadaan/List Pengajuan/popUpNegoBerhasil'), 3)){
		throw new StepErrorException('Price can not be less than base price or buyer price')
	}
}
else if(Integer.parseInt(buyer_new_nego) < Integer.parseInt(v_price_nego)){
	if(WebUI.verifyElementPresent(findTestObject('Object Repository/Pengadaan/List Pengajuan/popUpNegoFailed'), 3)){
		throw new StepErrorException('Price can not be less than base price or buyer price')
	}
	else if (WebUI.verifyElementPresent(findTestObject('Object Repository/Pengadaan/List Pengajuan/popUpNegoBerhasil'), 3)){
		println "continue"
	}
} 

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

WebUI.verifyElementPresent(findTestObject('Object Repository/General/txtSearch'), 2)

'Input Invoice Number in to search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify the invoice should exist'
WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Verify order status should be \'Order Diteruskan Ke Pengadaan\''
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Pengadaan/List Pengajuan/lblStatusOrder')), 'Order Diteruskan Ke Pengadaan',
	true)

'Click button Nego to continue negotiation'
WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnNegoOrder'))

'Click button Nego in desired item'
WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnNegoItem'))

'Close pop up nego'
WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/iconCloseNego'))

'Logout from user Vendor'
CustomKeywords.'common.logout.logoutUser'()

'Login as Pengadaan to approve order'
//WebUI.callTestCase(findTestCase('Transaksi/Pengadaan - Approve Order'), [:], FailureHandling.STOP_ON_FAILURE)

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

'Close browser'

