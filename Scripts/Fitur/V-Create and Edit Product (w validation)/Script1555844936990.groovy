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

CustomKeywords.'common.login.loginVendor'()

WebUI.waitForPageLoad(4)

WebUI.click(findTestObject('Vendor/Left Menu/lblProduct'))

WebUI.waitForPageLoad(4)

WebUI.click(findTestObject('General/iconTambah'))

WebUI.delay(2, FailureHandling.OPTIONAL)

rnd_code = RandStr.randomNumeric(5)

def image = CustomKeywords.'common.folderPath.imageDir'()

WebUI.uploadFile(findTestObject('Vendor/Product/btnChooseFileThumbnail'), image)

WebUI.click(findTestObject('General/btnSubmit'))

while (WebUI.verifyElementPresent(findTestObject('Object Repository/Vendor/Product/popUpValidation'), 4, FailureHandling.OPTIONAL)){
	if (WebUI.getAttribute(findTestObject('Vendor/Product/txtProductCode'), 'value', FailureHandling.OPTIONAL) == ''){
		WebUI.setText(findTestObject('Vendor/Product/txtProductCode'), product_code + rnd_code)
	}
	else if (WebUI.getAttribute(findTestObject('Vendor/Product/txtProductName'), 'value', FailureHandling.OPTIONAL) == ''){
		WebUI.setText(findTestObject('Vendor/Product/txtProductName'), (product_name + '-') + rnd_code, FailureHandling.OPTIONAL)
	}
	else if (WebUI.getAttribute(findTestObject('Vendor/Product/lstCategory'), 'value') == ''){
		WebUI.click(findTestObject('Vendor/Product/lstCategory'))
		
		WebUI.selectOptionByLabel(findTestObject('Vendor/Product/lstCategory'), 'Makanan Ringan', false)
	}
	else if (WebUI.getAttribute(findTestObject('Vendor/Product/txtStock'), 'value') == ''){
		WebUI.setText(findTestObject('Vendor/Product/txtStock'), stock)
	}
	else if (WebUI.getAttribute(findTestObject('Vendor/Product/txtPrice'), 'value') == ''){
		WebUI.setText(findTestObject('Vendor/Product/txtPrice'), price)
	}
	else if (WebUI.getAttribute(findTestObject('Vendor/Product/txtWaktuPenyediaan'), 'value') == ''){
		WebUI.setText(findTestObject('Vendor/Product/txtWaktuPenyediaan'), availability)
	}
	else if (WebUI.getAttribute(findTestObject('Vendor/Product/lstNego'), 'value') == ''){
		WebUI.click(findTestObject('Vendor/Product/lstNego'))
		
		WebUI.selectOptionByLabel(findTestObject('Vendor/Product/lstNego'), 'Bisa Nego', false)
	}
	else if (WebUI.getAttribute(findTestObject('Vendor/Product/txtDescription'), 'value') == ''){
		WebUI.setText(findTestObject('Vendor/Product/txtDescription'), description)
	}
	WebUI.click(findTestObject('General/btnSubmit'), FailureHandling.OPTIONAL)
}

//WebUI.click(findTestObject('Vendor/Product/lstProductType'))

//WebUI.selectOptionByLabel(findTestObject('Vendor/Product/lstProductType'), 'Barang', false)

//WebUI.setText(findTestObject('Vendor/Product/txtTax'), tax)

WebUI.waitForPageLoad(4)

WebUI.refresh()

WebUI.setText(findTestObject('General/txtSearch'), product_code + rnd_code)

WebUI.click(findTestObject('General/btnEdit'))

//WebUI.setText(findTestObject('Vendor/Product/txtEditProductCode'), new_product_code + rnd_code)

WebUI.setText(findTestObject('Vendor/Product/txtEditProductName'), (new_product_name + '-') + rnd_code)

WebUI.click(findTestObject('Vendor/Product/lstEditCategory'), FailureHandling.STOP_ON_FAILURE)

WebUI.selectOptionByLabel(findTestObject('Vendor/Product/lstEditCategory'), 'Makanan Rapat', false)

WebUI.setText(findTestObject('Vendor/Product/txtEditStock'), new_stock)

WebUI.setText(findTestObject('Vendor/Product/txtEditPrice'), new_price)

WebUI.setText(findTestObject('Vendor/Product/txtEditWaktuPenyediaan'), new_availability)

WebUI.click(findTestObject('Vendor/Product/lstEditNego'))

WebUI.selectOptionByLabel(findTestObject('Vendor/Product/lstEditNego'), 'Tidak Bisa', false)

WebUI.setText(findTestObject('Vendor/Product/txtEditDescription'), new_description)

//WebUI.click(findTestObject('Vendor/Product/lstEditProductType'))

//WebUI.selectOptionByLabel(findTestObject('Vendor/Product/lstEditProductType'), 'Barang', false)

//WebUI.setText(findTestObject('Vendor/Product/txtEditTax'), new_tax)

WebUI.delay(4)

WebUI.click(findTestObject('Vendor/Product/btnUpdateProduct'))

WebUI.waitForPageLoad(4)

WebUI.refresh()

WebUI.setText(findTestObject('General/txtSearch'), (new_product_name + '-') + rnd_code)

WebUI.click(findTestObject('Object Repository/General/btnDelete'))

WebUI.click(findTestObject('Object Repository/General/btnDeleteOK'))

WebUI.waitForPageLoad(4)

WebUI.refresh()

WebUI.setText(findTestObject('General/txtSearch'), (new_product_name + '-') + rnd_code)

WebUI.verifyTextNotPresent((new_product_name + '-') + rnd_code, false)

CustomKeywords.'common.logout.logoutUser'()

WebUI.closeBrowser()

