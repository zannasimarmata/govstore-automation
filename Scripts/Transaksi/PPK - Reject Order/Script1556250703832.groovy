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
import org.eclipse.persistence.internal.oxm.record.json.JSONParser.value_return as value_return
import org.openqa.selenium.Keys as Keys

'Login as PPK'
CustomKeywords.'common.login.loginPPK'()

'Move to menu List Pengajuan'
WebUI.click(findTestObject('Object Repository/PPK/Left Menu/lblListPengajuan'))

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

'Input Invoice Number in to search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify the Invoice No should be exist'
if (WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true) == true) {
    println('Orderan ada')
} else {
    println('Orderan tidak ada')
}

'Verify new order status should be \'Order Masuk\''
WebUI.verifyMatch(WebUI.getText(findTestObject('PPK/List Pengajuan/lblStatusOrder')), 'Order Masuk', true)

'Click button Detail'
WebUI.click(findTestObject('Object Repository/PPK/List Pengajuan/btnDetail'))

'Click button Reject'
WebUI.click(findTestObject('PPK/List Pengajuan/btnReject'))

'Verify Reject pop up appear'
WebUI.verifyElementPresent(findTestObject('Object Repository/PPK/List Pengajuan/popUpRejectOrder'), 3)

'Input reason for Reject'
WebUI.setText(findTestObject('Object Repository/PPK/List Pengajuan/txtAlasanReject'), 'Tests')

'Click Kirim'
WebUI.click(findTestObject('Object Repository/PPK/List Pengajuan/btnKirimReject'))

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

'Input Invoice Number in to search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify Invoice should not exist anymore in menu \'List Pengajuan\''
WebUI.verifyTextNotPresent(GlobalVariable.InvoiceNo, true)

/*Validasi Submit Rejection without reason*/
/*
if (tempAlasanReject == '') {
    WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)
	
	'Menampung status order yang baru dibuat'
	statusOrder = WebUI.getText(findTestObject('PPK/List Pengajuan/lblStatusOrder'))

    'Mengecek status order baru harus = Order Masuk'
    WebUI.verifyMatch(findTestObject('PPK/List Pengajuan/lblStatusOrder'), 'Order Masuk', true)

    if (WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true) == true) {
        println('Orderan ada')
    } else {
        println('Orderan tidak ada')
    }
    
    WebUI.click(findTestObject('Object Repository/PPK/List Pengajuan/btnDetail'))

    'Klik tombol Reject'
    WebUI.click(findTestObject('PPK/List Pengajuan/btnReject'))

    'Verifikasi pop up reject muncul'
    WebUI.verifyElementPresent(findTestObject('Object Repository/PPK/List Pengajuan/popUpRejectOrder'), 3)

    WebUI.setText(findTestObject('Object Repository/PPK/List Pengajuan/txtAlasanReject'), 'Tests')

    'Klik Kirim tanpa mengisi alasan Reject'
    WebUI.click(findTestObject('Object Repository/PPK/List Pengajuan/btnKirimReject'))
}
*/
'Move to menu \'Status Proses Pengajuan\''
WebUI.click(findTestObject('Object Repository/PPK/Left Menu/lblStatusProsesPengajuan'))

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

'Input Invoice Number in to search bar'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), GlobalVariable.InvoiceNo)

'Verify the Invoice No should be exist'
WebUI.verifyTextPresent(GlobalVariable.InvoiceNo, true)

'Verify order status, should be \'Order Ditolak PPK\''
WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/PPK/Status Proses Pengajuan/lblStatusPengajuan')), 'Order Ditolak PPK', true)

'Click button Detail'
WebUI.click(findTestObject('Object Repository/PPK/Status Proses Pengajuan/btnDetail'))

'Click Close'
WebUI.click(findTestObject('Object Repository/PPK/Status Proses Pengajuan/iconClose'))

'Logout from user PPK'
CustomKeywords.'common.logout.logoutUser'()

'Wait for page fully loaded in 4 secs'
WebUI.waitForPageLoad(4)

