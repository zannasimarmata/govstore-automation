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

'Login dengan user Admin\r\n'
CustomKeywords.'common.login.loginAdminSatker'()

'Klik menu Category'
WebUI.click(findTestObject('Satker/Left Menu/Category'))

WebUI.waitForPageLoad(4)

'Random string'
catName = RandStr.randomNumeric(3)

'Klik ikon Tambah untuk menambah category baru'
WebUI.click(findTestObject('Satker/Menu Category/iconTambahCategory'))

'Simpan category baru'
WebUI.click(findTestObject('Satker/Menu Category/btnSubmit'))

/*Validasi Category Name*/
while (WebUI.verifyElementPresent(findTestObject('Satker/Menu Category/popUpAddFailed'), 4, FailureHandling.OPTIONAL)) {
	if (WebUI.getAttribute(findTestObject('Satker/Menu Category/txtCategoryName'), 'value', FailureHandling.OPTIONAL) == '') {
		println('Category Name kosong')

		'Input nama category baru'
		WebUI.setText(findTestObject('Satker/Menu Category/txtCategoryName'), (nama_category + '-') + catName)
	}
	else if (WebUI.getAttribute(findTestObject('Satker/Menu Category/lstJenisCategory'), 'value', FailureHandling.OPTIONAL) == '') {
        println('Category Type belum dipilih')

        'Klik dropdown Jenis Category'
        WebUI.click(findTestObject('Satker/Menu Category/lstJenisCategory'))

        'Pilih Jenis Category'
        WebUI.selectOptionByLabel(findTestObject('Satker/Menu Category/lstJenisCategory'), 'Barang', false)
	}
	WebUI.click(findTestObject('Satker/Menu Category/btnSubmit'))
}

'Menunggu page selesai loading selama 4 detik'
WebUI.waitForPageLoad(4)

WebUI.waitForElementPresent(findTestObject('Object Repository/General/txtSearch'), 4)

WebUI.refresh()

'Cari category yang baru ditambah'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), (nama_category + '-') + catName)

WebUI.verifyElementText(findTestObject('Satker/Menu Category/listCategory'), (nama_category + '-') + catName)

'Edit category yang sudah dicari'
WebUI.click(findTestObject('Satker/Menu Category/btnEditCategory'))

WebUI.waitForElementVisible(findTestObject('Satker/Menu Category/lblEditCategory'), 2)

'Hapus nama category'
WebUI.clearText(findTestObject('Satker/Menu Category/txtEditCatName'))

'Simpan hasil update category'
WebUI.click(findTestObject('Satker/Menu Category/btnUpdateCategory'))

'Validasi Category Name ketika Edit'
while (WebUI.verifyElementPresent(findTestObject('Satker/Menu Category/popUpUpdateFailed'), 4, FailureHandling.OPTIONAL)){
	println 'Gagal update'
	
	'Menunggu page selesai loading selama 4 detik'
	WebUI.waitForPageLoad(4)
	
	'Cari category yang baru ditambah'
	WebUI.setText(findTestObject('Object Repository/General/txtSearch'), (nama_category + '-') + catName)
	
	WebUI.verifyElementText(findTestObject('Satker/Menu Category/listCategory'), (nama_category + '-') + catName)
	
	'Edit category yang sudah dicari'
	WebUI.click(findTestObject('Satker/Menu Category/btnEditCategory'))
	
	WebUI.waitForElementVisible(findTestObject('Satker/Menu Category/lblEditCategory'), 2)
	
	'Hapus nama category'
	WebUI.clearText(findTestObject('Satker/Menu Category/txtEditCatName'))
	
	'Edit nama category'
	WebUI.setText(findTestObject('Satker/Menu Category/txtEditCatName'), (new_nama_category + '-') + catName)
		
	'Simpan hasil update category'
	WebUI.click(findTestObject('Satker/Menu Category/btnUpdateCategory'))
}

/*
if (WebUI.verifyElementPresent(findTestObject('Satker/Menu Category/popUpAddFailed'), 4)){
	println 'Gagal update'
	
	if (WebUI.getAttribute(findTestObject('Satker/Menu Category/txtEditCatName'), 'value') == ''){
		println 'Category Name kosong'
		
		'Edit nama category'
		WebUI.setText(findTestObject('Satker/Menu Category/txtEditCatName'), (new_nama_category + '-') + catName)
		
		'Simpan hasil update category'
		WebUI.click(findTestObject('Satker/Menu Category/btnUpdateCategory'))
	}
	else
		println 'Category Name tidak kosong'
}
else
	println 'Berhasil update'
*/

'Menunggu page selesai loading selama 4 detik'
WebUI.waitForPageLoad(4)

'Cari category dengan nama yang sudah diupdate'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), (new_nama_category + '-') + catName)

'Hapus category'
WebUI.click(findTestObject('General/btnDelete'))

'Confirm delete category'
WebUI.click(findTestObject('General/btnDeleteOK'))

'Menunggu page selesai loading selama 4 detik'
WebUI.waitForPageLoad(4)

'Cari category dengan nama yang sudah dihapus'
WebUI.setText(findTestObject('Object Repository/General/txtSearch'), (new_nama_category + '-') + catName)

'Verifikasi category yang sudah dihapus sudah tidak muncul lagi'
if (WebUI.verifyTextNotPresent((new_nama_category + '-') + catName, false) == true) {
    println('Category sudah dihapus')
} else {
    println('Category gagal dihapus')
}

'Logout'
CustomKeywords.'common.logout.logoutUser'()

WebUI.closeBrowser()

