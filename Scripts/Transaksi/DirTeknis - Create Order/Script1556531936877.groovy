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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.apache.commons.lang.RandomStringUtils as RandStr

WebUI.callTestCase(findTestCase('General/Open Browser'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'common.login.loginDirTeknis'()

WebUI.click(findTestObject('DirTeknis/Left Menu/lblCatalog'))

WebUI.waitForPageLoad(4)

WebUI.setText(findTestObject('DirTeknis/Order/txtSearchProduct'), product_name)

WebUI.click(findTestObject('DirTeknis/Order/lblProductName'))

'Get Stock of item that will be ordered'
stock = WebUI.getText(findTestObject('Object Repository/DirTeknis/Order/lblJumlahStok'))

'Input qty to be ordered'
WebUI.setText(findTestObject('DirTeknis/Order/txtInputJumlahBeli'), stock_to_buy)

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

'Klik submit tanpa mengisi data checkout'
WebUI.click(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnSend'))

if (WebUI.verifyElementPresent(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/popUpOrderFailed'), 4) == true){
	println 'Gagal checkout'
	
	if (WebUI.getAttribute(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/txtNote'), 'value') == ''){
		println 'Note masih kosong'
		
		WebUI.setText(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/txtNote'), note)
		
		'Klik submit untuk checkout'
		WebUI.click(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnSend'))
	}
	else
		println 'Note tidak kosong'
}
else
	println 'Berhasil checkout'

/*Validasi Consignee*/
	
if (WebUI.verifyElementPresent(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/popUpOrderFailed'), 4) == true){
	println 'Gagal checkout'
	
	if (WebUI.getAttribute(findTestObject('DirTeknis/Order/Pop Up Checkout/txtNamaPenerima'), 'value') == ''){
		println 'Consignee masih kosong'
		
		'Input Consignee'
		WebUI.setText(findTestObject('DirTeknis/Order/Pop Up Checkout/txtNamaPenerima'), consignee)
		
		'Klik submit untuk checkout'
		WebUI.click(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnSend'))
	}
	else
		println 'Consignee tidak kosong'
}
else
	println 'Berhasil checkout'

/*Validasi Consignee Phone*/
	
if (WebUI.verifyElementPresent(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/popUpOrderFailed'), 4) == true){
	println 'Gagal checkout'
	
	if (WebUI.getAttribute(findTestObject('DirTeknis/Order/Pop Up Checkout/txtNoTelpPenerima'), 'value') == ''){
		println 'Consignee Phone masih kosong'
		
		WebUI.setText(findTestObject('DirTeknis/Order/Pop Up Checkout/txtNoTelpPenerima'), consignee_phone + rndnumber)
		
		'Klik submit untuk checkout'
		WebUI.click(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnSend'))
	}
	else
		println 'Consignee Phone tidak kosong'
}
else
	println 'Berhasil checkout'

/*Validasi Delivery Address*/
	
if (WebUI.verifyElementPresent(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/popUpOrderFailed'), 4) == true){
	println 'Gagal checkout'
	
	if (WebUI.getAttribute(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/txtDeliveryAddress'), 'value') == ''){
		println 'Delivery Address masih kosong'
		
		'Input Delivery Address'
		WebUI.setText(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/txtDeliveryAddress'), delivery_address)
		
		'Klik submit untuk checkout'
		WebUI.click(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnSend'))
	}
	else
		println 'Delivery Address tidak kosong'
}
else
	println 'Berhasil checkout'
	
/*Validasi Time Must be Received (ETA)*/
	
if (WebUI.verifyElementPresent(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/popUpOrderFailed'), 4) == true){
	println 'Gagal checkout'
	
	if (WebUI.getAttribute(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/txtETA'), 'value') == ''){
		println 'Time Must be Received masih kosong'
		
		'Input Time Must be Received'
		WebUI.setText(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/txtETA'), ETA)
		
		'Klik submit untuk checkout'
		WebUI.click(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnSend'))
	}
	else
		println 'Time Must be Received tidak kosong'
}
else
	println 'Berhasil checkout'
	
/*Validasi Kode Kegiatan*/
	
if (WebUI.verifyElementPresent(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/popUpOrderFailed'), 4) == true){
	println 'Gagal checkout'
	
	if (WebUI.getAttribute(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/txtKodeKegiatan'), 'value') == ''){
		println 'Kode Kegiatan masih kosong'
		
		'Input Kode Kegiatan'
		WebUI.setText(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/txtKodeKegiatan'), kode_kegiatan)
		
		'Klik submit untuk checkout'
		WebUI.click(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnSend'))
	}
	else
		println 'Kode Kegiatan tidak kosong'
}
else
	println 'Berhasil checkout'

/*Validasi Upload File Nota Dinas*/
	
if (WebUI.verifyElementPresent(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/popUpOrderFailed'), 4) == true){
	
	if (WebUI.getAttribute(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnUploadFile'), 'value') == '')
	{
		println 'Nota dinas masih kosong'
		'Memanggil fungsi upload file'
		def filesupload = CustomKeywords.'common.folderPath.filesDir'()
		
		'Upload File'
		WebUI.uploadFile(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnUploadFile'), filesupload)
		
		'Klik submit untuk checkout'
		WebUI.click(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnSend'))
	}
	else
		println 'Upload File tidak kosong'
}
else
	println 'Berhasil checkout'
	
'Click Geo Location'
//WebUI.click(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnGeoLocation'))
//WebUI.click(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnOKMap'))

'Klik submit untuk checkout'
//WebUI.click(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnSend'))
currentDateTime = CustomKeywords.'common.login.getCurrentTime'()
println (currentDateTime)


/*Validasi No Telp : bukan mandatory*/
    /*
if (WebUI.verifyElementPresent(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/txtNoTelp'), 4) == true){
	println 'Gagal checkout'
	
	if (WebUI.getAttribute(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/txtNoTelp'), 'value') == ''){
		println 'No Telp masih kosong'
		
		'Input No Telp'
		WebUI.setText(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/txtNoTelp'), no_telp + rndnumber)
		
		'Klik submit untuk checkout'
		WebUI.click(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnSend'))
	}
	else
		println 'No Telp tidak kosong'
}
else
	println 'Berhasil checkout'
*/
    
	
WebUI.waitForElementPresent(findTestObject('Object Repository/DirTeknis/Order/Pop Up Checkout/btnOrderLagi'), 4)

WebUI.click(findTestObject('Object Repository/DirTeknis/Left Menu/lblListOrder'))

WebUI.waitForPageLoad(4)

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

'User logout'
CustomKeywords.'common.logout.logoutUser'()

WebUI.waitForPageLoad(4)


