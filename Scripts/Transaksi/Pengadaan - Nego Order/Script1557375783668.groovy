import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.supercsv.cellprocessor.ParseInt as ParseInt
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
import com.kms.katalon.core.exception.StepFailedException as StepFailedException

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

'Click button Detail to see Order details'
WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnNegoOrder'))

'Get base price item'
base_price_idr = WebUI.getText(findTestObject('Object Repository/Pengadaan/List Pengajuan/lblPriceItemNego'))
println(base_price_idr)
base_price = base_price_idr.replaceAll('Rp ', '')
fix_base_price = base_price.replace('.', '')
println(fix_base_price)


'Click button Nego in desired item'
WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnNegoItem'))

'Input Harga Nego'
WebUI.setText(findTestObject('Object Repository/Pengadaan/List Pengajuan/txtHargaNego'), harga_nego_buyer)

'Click Nego to send nego price to vendor'
WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnConfirmNego'))

//nego_buyer = WebUI.getAttribute(findTestObject('Object Repository/Pengadaan/List Pengajuan/txtHargaNego'), 'value')
//print (nego_buyer)
println(harga_nego_buyer)

/*
 if (((Integer.parseInt(harga_nego_buyer) <= 0) && (WebUI.verifyElementPresent(findTestObject('Object Repository/Pengadaan/List Pengajuan/popUpNegoBerhasil'), 4, FailureHandling.OPTIONAL))) || (Integer.parseInt(harga_nego_buyer) > Integer.parseInt(fix_base_price)) && (WebUI.verifyElementPresent(findTestObject('Object Repository/Pengadaan/List Pengajuan/popUpNegoBerhasil'), 4, FailureHandling.OPTIONAL)))
 {
	 throw new StepFailedException('Nego Price should be greater than 0')
 }
 else if (WebUI.verifyElementPresent(findTestObject('Object Repository/Pengadaan/List Pengajuan/popUpNegoFailed'), 2)){
	 while (WebUI.verifyElementPresent(findTestObject('Object Repository/Pengadaan/List Pengajuan/popUpNegoFailed'), 2, FailureHandling.OPTIONAL)){
		 WebUI.waitForPageLoad(4)
		 
		 'Input Invoice Number into search bar'
		 WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)
	 
		 'Verify the invoice should exist'
		 WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)
		 
		 'Verify order status should be \'Order Diteruskan Ke Pengadaan\''
		 WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Pengadaan/List Pengajuan/lblStatusOrder')), 'Order Diteruskan Ke Pengadaan',
			 true)
		 
		 'Click button Detail to see Order details'
		 WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnNegoOrder'))
	 
		 'Click button Nego in desired item'
		 WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnNegoItem'))
	 
		 'Input Harga Nego'
		 WebUI.setText(findTestObject('Object Repository/Pengadaan/List Pengajuan/txtHargaNego'), new_harga_nego_buyer)
	 
		 'Click Nego to send nego price to vendor'
		 WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnConfirmNego'))
	 }
 }
 else {
	 println 'Good to go'
 }


if (((Integer.parseInt(harga_nego_buyer) <= 0) || (Integer.parseInt(harga_nego_buyer) > Integer.parseInt(fix_base_price))) && (WebUI.verifyElementPresent(findTestObject('Object Repository/Pengadaan/List Pengajuan/popUpNegoBerhasil'), 4, FailureHandling.OPTIONAL)))
{
	throw new StepFailedException('Nego Price should be greater than 0')
}
else if (WebUI.verifyElementPresent(findTestObject('Object Repository/Pengadaan/List Pengajuan/popUpNegoFailed'), 2)){
	while (WebUI.verifyElementPresent(findTestObject('Object Repository/Pengadaan/List Pengajuan/popUpNegoFailed'), 2, FailureHandling.OPTIONAL)){
		WebUI.waitForPageLoad(4)
		
		'Input Invoice Number into search bar'
		WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)
	
		'Verify the invoice should exist'
		WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)
		
		'Verify order status should be \'Order Diteruskan Ke Pengadaan\''
		WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Pengadaan/List Pengajuan/lblStatusOrder')), 'Order Diteruskan Ke Pengadaan',
			true)
		
		'Click button Detail to see Order details'
		WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnNegoOrder'))
	
		'Click button Nego in desired item'
		WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnNegoItem'))
	
		'Input Harga Nego'
		WebUI.setText(findTestObject('Object Repository/Pengadaan/List Pengajuan/txtHargaNego'), new_harga_nego_buyer)
	
		'Click Nego to send nego price to vendor'
		WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnConfirmNego'))	
	}
}
else {
	println 'Good to go'
}
*/

if (((Integer.parseInt(harga_nego_buyer) < 0) && (WebUI.verifyElementPresent(findTestObject('Object Repository/Pengadaan/List Pengajuan/popUpNegoFailed'), 
    4) == true)) || ((Integer.parseInt(harga_nego_buyer) > Integer.parseInt(fix_base_price)) && (WebUI.verifyElementPresent(
    findTestObject('Object Repository/Pengadaan/List Pengajuan/popUpNegoFailed'), 2) == true))) {
    
	WebUI.waitForPageLoad(4)

    'Input Invoice Number into search bar'
    WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

    'Verify the invoice should exist'
    WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

    'Verify order status should be \'Order Diteruskan Ke Pengadaan\''
    WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Pengadaan/List Pengajuan/lblStatusOrder')), 'Order Diteruskan Ke Pengadaan', 
        true)

    'Click button Detail to see Order details'
    WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnNegoOrder'))

    'Click button Nego in desired item'
    WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnNegoItem'))

    'Input Harga Nego'
    WebUI.setText(findTestObject('Object Repository/Pengadaan/List Pengajuan/txtHargaNego'), new_harga_nego_buyer)

    'Click Nego to send nego price to vendor'
    WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnConfirmNego'))
}
else if (((Integer.parseInt(harga_nego_buyer) < 0) && (WebUI.verifyElementPresent(findTestObject('Object Repository/Pengadaan/List Pengajuan/popUpNegoBerhasil'), 
    4) == true)) || ((Integer.parseInt(harga_nego_buyer) > Integer.parseInt(fix_base_price)) && (WebUI.verifyElementPresent(
    findTestObject('Object Repository/Pengadaan/List Pengajuan/popUpNegoBerhasil'), 2) == true)))
{
    throw new StepFailedException('Nego Price should be greater than 0')
}
else
{
    println 'continue'
}


'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

WebUI.verifyElementPresent(findTestObject('Object Repository/General/txtSearch'), 2)

'Input Invoice Number into search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify the invoice should exist'
WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Verify order status should be \'Order Diteruskan Ke Pengadaan\''
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Pengadaan/List Pengajuan/lblStatusOrder')), 'Order Diteruskan Ke Pengadaan',
	true)

'Click button Detail to see Order details'
WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnNegoOrder'))

'Click button Nego in desired item'
WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/btnNegoItem'))

'Click close'
WebUI.click(findTestObject('Object Repository/Pengadaan/List Pengajuan/iconCloseNego'))

/*
 * Login as Vendor | Calling testcase*/

'Logout from user Pengadaan'
CustomKeywords.'common.logout.logoutUser'()

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

//WebUI.callTestCase(findTestCase('Transaksi/Vendor - Nego Order'), [('vendor_price_nego') : '9000', ('new_vendor_price_nego') : '10000'], FailureHandling.STOP_ON_FAILURE)

