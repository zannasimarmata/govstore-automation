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

'Memanggil testcase untuk buka browser'
WebUI.callTestCase(findTestCase('General/Open Browser'), [:], FailureHandling.STOP_ON_FAILURE)

'Login dengan user Admin Satker'
CustomKeywords.'common.login.loginPengadaan'()

WebUI.waitForPageLoad(4)

'Klik menu Daftar Vendor dari Left Menu'
WebUI.click(findTestObject('Satker/Left Menu/lblDaftarVendor'))

'Klik Tambah untuk tambah Vendor'
WebUI.click(findTestObject('General/iconTambah'))

'random 4 digits'
rnd_iddistributor = RandStr.randomNumeric(4)

'Memanggil fungsi upload image dari custom keywords'
def image = CustomKeywords.'common.folderPath.imageDir2'()

'Upload image dari folder Images'
WebUI.uploadFile(findTestObject('Satker/Menu Vendor/btnChooseFile'), image)

'Input ID Distributor'
WebUI.setText(findTestObject('Satker/Menu Vendor/txtIdDistributor'), id_distributor + ('-' + rnd_iddistributor))

'Input NIK/SIUP'
WebUI.setText(findTestObject('Object Repository/Satker/Menu Vendor/txtNikSiup'), rnd_iddistributor + '/' + 000  + '-01.' + '1')

rnd_npwp = RandStr.randomNumeric(12)
'Input NPWP'
WebUI.setText(findTestObject('Object Repository/Satker/Menu Vendor/txtNpwp'), rnd_npwp + '001')

'Input nama distributor'
WebUI.setText(findTestObject('Satker/Menu Vendor/txtNamaDistributor'), nama_distributor + (' ' + rnd_iddistributor))

'Input email distributor'
WebUI.setText(findTestObject('Satker/Menu Vendor/txtEmailDistributor'), (id_distributor + ('.' + rnd_iddistributor)) + '@gmail.com')

'Random 10 angka untuk nomor telepon'
rnd_notelp = RandStr.randomNumeric(10)

'Input nomor telepon'
WebUI.setText(findTestObject('Satker/Menu Vendor/txtNoTelpDistributor'), no_telp + rnd_notelp)

'Input no rek BRI distributor'
WebUI.setText(findTestObject('Satker/Menu Vendor/txtNoRekBRI'), norek_bri)

'Input alamat distributor'
WebUI.setText(findTestObject('Satker/Menu Vendor/txtAlamatDistributor'), alamat_distributor)

'Simpan data distributor baru'
WebUI.click(findTestObject('General/btnSubmit'))

'Waiting for page loaded 4 secs'
WebUI.waitForPageLoad(4)

'Refresh page'
WebUI.refresh()

/*
 * Edit Distributor
 */

'Cari distributor yang baru ditambahkan'
WebUI.setText(findTestObject('General/txtSearch'), nama_distributor + (' ' + rnd_iddistributor))

'Verifikasi apakah nama distributor yang dicari sudah muncul'
WebUI.verifyTextPresent(nama_distributor + (' ' + rnd_iddistributor), false)

'Klik button Edit'
WebUI.click(findTestObject('General/btnEdit'))

'Edit NIK/SIUP'
WebUI.setText(findTestObject('Object Repository/Satker/Menu Vendor/txtEditNIKSIUP'), '312' + rnd_iddistributor)

'Edit NPWP'
WebUI.setText(findTestObject('Object Repository/Satker/Menu Vendor/txtEditNPWP'), rnd_npwp + '000')

'Edit nama Distributor; Disable jika tidak ingin mengganti nama Distributor'
WebUI.setText(findTestObject('Satker/Menu Vendor/txtEditNamaDistributor'), edit_nama_distributor + (' ' + rnd_iddistributor))

'Edit email Distributor; Disable jika tidak ingin mengganti email Distributor'
not_run: WebUI.setText(findTestObject('Satker/Menu Vendor/txtEditEmailDistributor'), (edit_nama_distributor + ('.' + rnd_iddistributor)) + 
    '@gmail.com')

'Random 10 angka untuk nomor telepon baru'
rnd_notelp2 = RandStr.randomNumeric(10)

'Edit nomor telepon Distributor; Disable jika tidak ingin mengganti nomor telepon Distributor'
WebUI.setText(findTestObject('Satker/Menu Vendor/txtEditNotelpDistributor'), no_telp + rnd_notelp2)

'Edit no rek BRI Distributor; Disable jika tidak ingin mengganti no rek BRI Distributor'
WebUI.setText(findTestObject('Satker/Menu Vendor/txtEditNorekBRI'), edit_norek_bri)

'Edit alamat Distributor; Disable jika tidak ingin mengganti alamat Distributor'
WebUI.setText(findTestObject('Satker/Menu Vendor/txtEditAlamatDistributor'), edit_alamat_distributor)

'Menyimpan perubahan distributor'
WebUI.click(findTestObject('General/btnUpdate'))

/*
 * End of Edit Vendor
 */


WebUI.waitForPageLoad(4)

/*
 * Delete Vendor
 */


WebUI.waitForElementPresent(findTestObject('General/txtSearch'), 4)

'Filter distributor berdasarkan nama distributor yang sudah diubah'
WebUI.setText(findTestObject('General/txtSearch'), edit_nama_distributor + (' ' + rnd_iddistributor))

'Klik tombol Delete'
WebUI.click(findTestObject('General/btnDelete'))

'Klik OK'
WebUI.click(findTestObject('Object Repository/General/btnDeleteOK'))

/*
 * End of Delete Vendor
 */

WebUI.waitForPageLoad(4)

WebUI.waitForElementPresent(findTestObject('General/txtSearch'), 4)

'Filter distributor berdasarkan nama distributor yang sudah diubah'
WebUI.setText(findTestObject('General/txtSearch'), edit_nama_distributor + (' ' + rnd_iddistributor))

WebUI.verifyTextNotPresent(edit_nama_distributor + (' ' + rnd_iddistributor), true)

'Logout'
CustomKeywords.'common.logout.logoutUser'()

'Tutup browser'
WebUI.closeBrowser()

