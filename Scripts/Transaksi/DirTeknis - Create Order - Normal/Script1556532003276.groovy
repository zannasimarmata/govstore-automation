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
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.apache.commons.lang.RandomStringUtils as RandStr
import com.kms.katalon.core.exception.StepErrorException as StepErrorException 

//WebUI.callTestCase(findTestCase('General/Open Browser'), [:], FailureHandling.STOP_ON_FAILURE)
WebUI.waitForPageLoad(4)

CustomKeywords.'common.login.loginDirTeknis'()

WebUI.click(findTestObject('DirTeknis/Left Menu/lblCatalog'))

WebUI.waitForPageLoad(4)

WebUI.setText(findTestObject('DirTeknis/Order/txtSearchProduct'), product_name)

WebUI.click(findTestObject('DirTeknis/Order/lblProductName'))

'Get Stock of item that will be ordered'
stock = WebUI.getText(findTestObject('Object Repository/DirTeknis/Order/lblJumlahStok'))

'Input qty to be ordered'
WebUI.setText(findTestObject('DirTeknis/Order/txtInputJumlahBeli'), stock_to_buy)

'Klik tambah ke keranjang'
WebUI.click(findTestObject('DirTeknis/Order/btnAddtoCart'))

'Compare stock left and stock inputted by Dirteknis'
if ((Integer.parseInt(stock) < Integer.parseInt(stock_to_buy)) && (WebUI.verifyElementPresent(findTestObject('Object Repository/DirTeknis/Order/popUpAddCartSuccess'), 3))) {
	println 'Stock tidak cukup'
	//WebUI.click(findTestObject('Object Repository/DirTeknis/Order/btnAddtoCart'), FailureHandling.STOP_ON_FAILURE)
	throw new StepErrorException('Stock tidak mencukupi')
}
else if(WebUI.verifyElementPresent(findTestObject('Object Repository/DirTeknis/Order/popUpAddtoCartFailed'), 3, FailureHandling.OPTIONAL)){
	while (WebUI.verifyElementPresent(findTestObject('Object Repository/DirTeknis/Order/popUpAddtoCartFailed'), 3, FailureHandling.OPTIONAL)){
		WebUI.setText(findTestObject('DirTeknis/Order/txtInputJumlahBeli'), stock_to_buy, FailureHandling.OPTIONAL)
		
		'Klik tambah ke keranjang'
		WebUI.click(findTestObject('DirTeknis/Order/btnAddtoCart'), FailureHandling.OPTIONAL)
	}
}
else {
	println 'Good to go'
}


'Menunggu sampai tombol Checkout muncul'
WebUI.waitForElementPresent(findTestObject('DirTeknis/Order/btnCheckout'), 4)

'Klik tombol Checkout'
WebUI.click(findTestObject('Object Repository/DirTeknis/Order/btnCheckout'))

rndnumber = RandStr.randomNumeric(5)

WebUI.setText(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/txtNote'), note)

'Input Consignee'
WebUI.setText(findTestObject('DirTeknis/Order/Pop Up Checkout/txtNamaPenerima'), consignee)
		
WebUI.setText(findTestObject('DirTeknis/Order/Pop Up Checkout/txtNoTelpPenerima'), consignee_phone + rndnumber)

'Input No Telp'
WebUI.setText(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/txtNoTelp'), no_telp + rndnumber)

'Input Delivery Address'
WebUI.setText(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/txtDeliveryAddress'), delivery_address)

'Input Time Must be Received'
WebUI.setText(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/txtETA'), ETA)

'Input Kode Kegiatan'
WebUI.setText(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/txtKodeKegiatan'), kode_kegiatan)

'Memanggil fungsi upload file'
def filesupload = CustomKeywords.'common.folderPath.filesDir'()
'Upload File'
WebUI.uploadFile(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnUploadFile'), filesupload)

'Click Geo Location'
//WebUI.click(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnGeoLocation'))
//WebUI.click(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnOKMap'))

WebUI.delay(4)

'Klik submit'
WebUI.click(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnSend'))

'Back to cart page'
WebUI.waitForElementPresent(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnOrderLagi'), 4)

/*
 * Checking stock left*//*
'Back to menu Order'
WebUI.click(findTestObject('DirTeknis/Left Menu/lblCatalog'))

'Insert product name to be searched'
WebUI.setText(findTestObject('DirTeknis/Order/txtSearchProduct'), product_name)

'Click product name'
WebUI.click(findTestObject('DirTeknis/Order/lblProductName'))

'Get Stock of item that will be ordered'
stock_left = WebUI.getText(findTestObject('Object Repository/DirTeknis/Order/lblJumlahStok'))

if (stock == stock_left){
	println 'Stock is not deducted. This is not right!'
	throw new StepErrorException('Stock left is wrong')
}
else{
	println 'This is good!'
}

/*
 * End of checking stock left*/



'Move to menu List Order'
WebUI.click(findTestObject('Object Repository/DirTeknis/Left Menu/lblListOrder'))

'waiting for page load for 4 secs'
WebUI.waitForPageLoad(4)

'Get date time of order created'
currentDateTime = CustomKeywords.'common.login.getCurrentTime'()

'Print Date time'
println (currentDateTime)

WebUI.setText(findTestObject('Object Repository/General/txtSearch'), currentDateTime)

/*
if (WebUI.verifyTextPresent(currentDateTime, true) == true){
	println 'Order sudah berhasil'
}
else{
	println 'Order gagal dibuat'
}
*/

'Set Invoice No into Global Variable'
GlobalVariable.InvoiceNo = WebUI.getText(findTestObject('Object Repository/DirTeknis/List Order/lblInvoiceNo'))
println GlobalVariable.InvoiceNo

'Verify status new order in menu List Order'
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/DirTeknis/List Order/lblStatusOrder')), 'Order Masuk', true)

'See order detail'
WebUI.click(findTestObject('Object Repository/DirTeknis/List Order/btnDetailOrder'))

'Close order detail'
WebUI.click(findTestObject('Object Repository/DirTeknis/List Order/iconClose'))

'Move to menu Report Order'
WebUI.click(findTestObject('Object Repository/DirTeknis/Left Menu/lblReportOrder'))

'Input text in search bar inside menu Report Order'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify if the invoice number is exist already'
WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Verify status new order in menu Report Order'
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/DirTeknis/List Order/lblStatusOrder')), 'Order Masuk', true)

'Click detail to see order detail'
WebUI.click(findTestObject('Object Repository/DirTeknis/Report Order/btnDetailOrder'))

'Close order detail'
WebUI.click(findTestObject('Object Repository/DirTeknis/Report Order/iconClose'))

'Logout from user DirTeknis'
CustomKeywords.'common.logout.logoutUser'()

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

//WebUI.closeBrowser()




