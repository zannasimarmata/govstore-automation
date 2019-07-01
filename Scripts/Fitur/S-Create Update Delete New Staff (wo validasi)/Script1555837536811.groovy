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

'Open browser'
WebUI.openBrowser('')

'Maximize browser'
WebUI.maximizeWindow()

'Buka web govstore'
WebUI.navigateToUrl('http://govstore.site')

'Login Govstore sebagai Admin Satker'
CustomKeywords.'common.login.loginAdminSatker'()

'Menunggu page load selama 4 detik'
WebUI.waitForPageLoad(4)

'Klik menu Staff yang ada di left menu'
WebUI.click(findTestObject('Satker/Left Menu/lblDaftarStaff'))

'Delay selama 4 detik'
WebUI.delay(4)

'Klik Icon Tambah untuk tambah user'
WebUI.click(findTestObject('General/iconTambah'))

'Menunggu sampai textbox Id Agen muncul'
WebUI.waitForElementPresent(findTestObject('Satker/Menu Staff/txtIdAgen'), 4)

'Random string untuk ID Agen dan ID User (concat dengan email user)'
rnd_idAgen = RandStr.randomNumeric(5)

'Input Id Agen ke textbox ID Agen'
WebUI.setText(findTestObject('Satker/Menu Staff/txtIdAgen'), id_agen + ('-' + rnd_idAgen))

'Input nama agen ke textbox Nama Agen'
WebUI.setText(findTestObject('Satker/Menu Staff/txtNama'), (id_agen + ' ') + rnd_idAgen)

'Input nomor telepon agen ke textbox Telepon'
WebUI.setText(findTestObject('Satker/Menu Staff/txtTelepon'), telepon)

'Input email agen ke textbox email'
WebUI.setText(findTestObject('Satker/Menu Staff/txtEmail'), (id_agen + ('.' + rnd_idAgen)) + '@gmail.com')

'Mengklik dropdown tipe agen'
WebUI.click(findTestObject('Satker/Menu Staff/lstTipe'))

'Memilih tipe agen yang baru ditambahkan'
WebUI.selectOptionByLabel(findTestObject('Satker/Menu Staff/lstTipe'), 'Dir Teknis', false)

'Menyimpan data agen baru yang ditambahkan'
WebUI.click(findTestObject('General/btnSubmit'))

'Menunggu page load selama 6 detik'
WebUI.waitForPageLoad(6)

'Refresh halaman'
WebUI.refresh()

'Input nama agen di field search untuk filter'
WebUI.setText(findTestObject('General/txtSearch'), id_agen + ('-' + rnd_idAgen))

'Klik tombol detail agen yang sudah difilter'
WebUI.click(findTestObject('Satker/Menu Staff/btnDetail'))

'Untuk mengganti Nama Agen; Disable jika tidak ingin mengganti Nama Agen\r\n'
WebUI.setText(findTestObject('Satker/Menu Staff/txtEditNamaAgen'), new_suffix_nama + (' ' + rnd_idAgen))

'Random 10 angka untuk Telepon Agen'
rnd_notelp = RandStr.randomNumeric(10)

'Untuk mengganti Telepon Agen; Disable jika tidak ingin mengganti Telepon Agen\r\n'
WebUI.setText(findTestObject('Satker/Menu Staff/txtEditTeleponAgen'), domain_telp + rnd_notelp)

'Menyimpan hasil perubahan data agen'
WebUI.click(findTestObject('General/btnUpdate'))

'Menunggu page load selama 4 detik'
WebUI.waitForPageLoad(4)

'Input nama agen di field search untuk filter'
WebUI.setText(findTestObject('General/txtSearch'), new_suffix_nama + (' ' + rnd_idAgen))

'Klik delete untuk menghapus agen yang sudah dicari; Disable jika tidak ingin menghapus agen'
WebUI.click(findTestObject('General/btnDelete'))

'Menunggu page load selama 4 detik'
WebUI.waitForPageLoad(4)

'Input nama agen di field search untuk filter'
WebUI.setText(findTestObject('General/txtSearch'), new_suffix_nama + (' ' + rnd_idAgen))

'Untuk mengecek jika agen yang dicari sebelumnya sudah tidak ada di list agen'
WebUI.verifyTextNotPresent(new_suffix_nama + (' ' + rnd_idAgen), false)

'Logout dari Govstore'
CustomKeywords.'common.logout.logoutUser'()

'Menutup browser'
WebUI.closeBrowser()

