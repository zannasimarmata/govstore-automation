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
WebUI.navigateToUrl(GlobalVariable.govstoreUrl)

'Login Govstore sebagai Admin Satker'
CustomKeywords.'common.login.loginAdminSatker'()

'Menunggu page load selama 4 detik'
//WebUI.waitForPageLoad(4)

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

'Menyimpan data agen baru yang ditambahkan'
WebUI.click(findTestObject('General/btnSubmit'))

/*Validasi ID Agen*/
while (WebUI.verifyElementPresent(findTestObject('Object Repository/Satker/Menu Staff/popUpAddValidation'), 4, FailureHandling.OPTIONAL)){
	println 'Failed to add'
	
	if ((WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/txtIdAgen'), 'value', FailureHandling.OPTIONAL)) == ''){
		println 'Id Agen kosong'
		
		'Input Id Agen ke textbox ID Agen'
		WebUI.setText(findTestObject('Satker/Menu Staff/txtIdAgen'), id_agen + ('-' + rnd_idAgen))
	}
	else if ((WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/lstTipe'), 'value')) == ''){
		println 'Tipe Agen belum dipilih'
						
		'Mengklik dropdown tipe agen'
		WebUI.click(findTestObject('Satker/Menu Staff/lstTipe'))
		
		'Memilih tipe agen yang baru ditambahkan'
		WebUI.selectOptionByLabel(findTestObject('Satker/Menu Staff/lstTipe'), 'Dir Teknis', false)
	}
	else if ((WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/txtNama'), 'value')) == ''){
		println 'Nama Agen kosong'
			
		'Input nama agen ke textbox Nama Agen'
		WebUI.setText(findTestObject('Satker/Menu Staff/txtNama'), (id_agen + ' ') + rnd_idAgen)
	}
	else if ((WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/txtTelepon'), 'value')) == ''){
		println 'Telepon Agen kosong'
		
		'Input nomor telepon agen ke textbox Telepon'
		WebUI.setText(findTestObject('Satker/Menu Staff/txtTelepon'), telepon)
	}
	else if ((WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/txtEmail'), 'value')) == ''){
		println 'Email Agen kosong'
		
		'Input email agen ke textbox email'
		WebUI.setText(findTestObject('Satker/Menu Staff/txtEmail'), 'jono')
	}
	else {
		check_email = WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/txtEmail'), 'value')
		if (!((check_email.contains('@') || check_email.contains('.com')) || check_email.contains('.co.id'))) {
			println('Format Email salah')
			WebUI.setText(findTestObject('Satker/Menu Staff/txtEmail'), (id_agen + ('.' + rnd_idAgen)) + '@gmail.com')
		}
	}
	WebUI.click(findTestObject('General/btnSubmit'))
	println 'successs'
	
}

/*
if (WebUI.verifyElementPresent(findTestObject('Object Repository/Satker/Menu Staff/popUpAddValidation'), 4)){
	println 'Failed to add'
	if ((WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/txtIdAgen'), 'value')) == ''){
		println 'Id Agen kosong'
		
		'Input Id Agen ke textbox ID Agen'
		WebUI.setText(findTestObject('Satker/Menu Staff/txtIdAgen'), id_agen + ('-' + rnd_idAgen))
		WebUI.click(findTestObject('General/btnSubmit'))
	}
	else{
		println 'ID Agen sudah terisi'
	}
}
else{
	println 'Success to add'
}

/*Validasi Tipe Staff*//*
if ((WebUI.verifyElementPresent(findTestObject('Object Repository/Satker/Menu Staff/popUpAddValidation'), 4)) == true){
	println 'Failed to add'
	if ((WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/lstTipe'), 'value')) == ''){
		println 'Tipe Agen belum dipilih'
						
		'Mengklik dropdown tipe agen'
		WebUI.click(findTestObject('Satker/Menu Staff/lstTipe'))
		
		'Memilih tipe agen yang baru ditambahkan'
		WebUI.selectOptionByLabel(findTestObject('Satker/Menu Staff/lstTipe'), 'Dir Teknis', false)
		WebUI.click(findTestObject('General/btnSubmit'))
	}
	else{
		println 'Tipe Agen sudah dipilih'
	}
}
else{
	println 'Success to add'
}

/*Validasi Nama Agen*//*
if ((WebUI.verifyElementPresent(findTestObject('Object Repository/Satker/Menu Staff/popUpAddValidation'), 4)) == true){
	println 'Failed to add'
	if ((WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/txtNama'), 'value')) == ''){
		println 'Nama Agen kosong'
			
		'Input nama agen ke textbox Nama Agen'
		WebUI.setText(findTestObject('Satker/Menu Staff/txtNama'), (id_agen + ' ') + rnd_idAgen)
		WebUI.click(findTestObject('General/btnSubmit'))
	}
	else{
		println 'Nama Agen sudah terisi'
	}
}
else{
	println 'Success to add'
}

/*Validasi Nomor Telepon Agen*//*
if ((WebUI.verifyElementPresent(findTestObject('Object Repository/Satker/Menu Staff/popUpAddValidation'), 4)) == true){
	println 'Failed to add'
	if ((WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/txtTelepon'), 'value')) == ''){
		println 'Telepon Agen kosong'
		
		'Input nomor telepon agen ke textbox Telepon'
		WebUI.setText(findTestObject('Satker/Menu Staff/txtTelepon'), telepon)
		WebUI.click(findTestObject('General/btnSubmit'))
	}
	else{
		println 'Telepon Agen sudah terisi'
	}
}
else{
	println 'Success to add'
}

/*Validasi Email Agen*//*
if ((WebUI.verifyElementPresent(findTestObject('Object Repository/Satker/Menu Staff/popUpAddValidation'), 4)) == true){
	println 'Failed to add'
	if ((WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/txtEmail'), 'value')) == ''){
		println 'Email Agen kosong'
		
		'Input email agen ke textbox email'
		WebUI.setText(findTestObject('Satker/Menu Staff/txtEmail'), 'jono')
		WebUI.click(findTestObject('General/btnSubmit'))
	}
	else{
		println 'Email Agen sudah terisi'
	}
}
else{
	println 'Success to add'
}


/*Validasi format email*//*
if ((WebUI.verifyElementPresent(findTestObject('Object Repository/Satker/Menu Staff/popUpAddValidation'), 4)) == true){
	println 'Failed to add'
	check_email = WebUI.getAttribute(findTestObject('Object Repository/Satker/Menu Staff/txtEmail'), 'value')
	if (!((check_email.contains('@') || check_email.contains('.com')) || check_email.contains('.co.id'))) {
		println('Format Email salah')
		WebUI.setText(findTestObject('Satker/Menu Staff/txtEmail'), (id_agen + ('.' + rnd_idAgen)) + '@gmail.com')
		WebUI.click(findTestObject('General/btnSubmit'))
	}
	else{
		println 'Format email sudah benar'
	}
}
else{
	println 'Success to add'
}*/


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

