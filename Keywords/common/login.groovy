package common
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
//import groovy.time.TimeCategory
import org.openqa.selenium.Keys as Keys

class login {
	/**
	 * Login sebagai Admin
	 * */
	@Keyword
	def loginAdmin() {
		WebUI.setText(findTestObject('Admin/Login Page/txtUsername'), GlobalVariable.UsrAdmin)
		WebUI.setText(findTestObject('Admin/Login Page/txtPassword'), GlobalVariable.pswdAdmin)
		//WebUI.click(findTestObject('Admin/Login Page/btnMasuk'))
		WebUI.sendKeys(findTestObject('Admin/Login Page/txtPassword'), Keys.chord(Keys.ENTER))
	}

	/**
	 * Login sebagai DirTeknis 
	 * */
	@Keyword
	def loginDirTeknis() {
		WebUI.setText(findTestObject('Admin/Login Page/txtUsername'), GlobalVariable.UsrDirTeknis)
		WebUI.setText(findTestObject('Admin/Login Page/txtPassword'), GlobalVariable.pswdDirteknis)
		//WebUI.click(findTestObject('Admin/Login Page/btnMasuk'))
		WebUI.sendKeys(findTestObject('Admin/Login Page/txtPassword'), Keys.chord(Keys.ENTER))
	}

	/**
	 * Login sebagai PPK
	 * */
	@Keyword
	def loginPPK() {
		WebUI.setText(findTestObject('Admin/Login Page/txtUsername'), GlobalVariable.UsrPPK)
		WebUI.setText(findTestObject('Admin/Login Page/txtPassword'), GlobalVariable.pswdPPK)

		//WebUI.click(findTestObject('Admin/Login Page/btnMasuk'))
		WebUI.sendKeys(findTestObject('Admin/Login Page/txtPassword'), Keys.chord(Keys.ENTER))
	}

	/**
	 * Login sebagai Pengadaan
	 * */
	@Keyword
	def loginPengadaan() {
		WebUI.setText(findTestObject('Admin/Login Page/txtUsername'), GlobalVariable.UsrPengadaan)
		WebUI.setText(findTestObject('Admin/Login Page/txtPassword'), GlobalVariable.pswdPengadaan)
		//WebUI.click(findTestObject('Object Repository/Admin/Login Page/btnMasuk'))
		WebUI.sendKeys(findTestObject('Admin/Login Page/txtPassword'), Keys.chord(Keys.ENTER))
	}

	/**
	 * Login sebagai Vendor
	 * */
	@Keyword
	def loginVendor() {
		WebUI.setText(findTestObject('Admin/Login Page/txtUsername'), GlobalVariable.UsrVendor)
		WebUI.setText(findTestObject('Admin/Login Page/txtPassword'), GlobalVariable.pswdVendor)
		WebUI.click(findTestObject('Admin/Login Page/btnMasuk'))
		//WebUI.sendKeys(findTestObject('Admin/Login Page/txtPassword'), Keys.chord(Keys.ENTER))
	}

	/**
	 * Login sebagai AdminSatker
	 * */
	@Keyword
	def loginAdminSatker() {
		WebUI.setText(findTestObject('Admin/Login Page/txtUsername'), GlobalVariable.UsrSatker)
		WebUI.setText(findTestObject('Admin/Login Page/txtPassword'), GlobalVariable.pswdSatker)
		WebUI.click(findTestObject('Admin/Login Page/btnMasuk'))
		//WebUI.sendKeys(findTestObject('Admin/Login Page/txtPassword'), Keys.chord(Keys.ENTER))
	}

	/**
	 * Login sebagai Bendahara
	 * */
	@Keyword
	def loginBendahara() {
		WebUI.setText(findTestObject('Admin/Login Page/txtUsername'), GlobalVariable.UsrBendahara)
		WebUI.setText(findTestObject('Admin/Login Page/txtPassword'), GlobalVariable.pswdBendahara)
		//WebUI.click(findTestObject('Admin/Login Page/btnMasuk'))
		WebUI.sendKeys(findTestObject('Admin/Login Page/txtPassword'), Keys.chord(Keys.ENTER))
	}

	/**
	 * Login sebagai PKN
	 * */
	@Keyword
	def loginPKN() {
		WebUI.setText(findTestObject('Admin/Login Page/txtUsername'), GlobalVariable.UsrPKN)
		WebUI.setText(findTestObject('Admin/Login Page/txtPassword'), GlobalVariable.pswdPKN)
		//WebUI.click(findTestObject('Admin/Login Page/btnMasuk'))
		WebUI.sendKeys(findTestObject('Admin/Login Page/txtPassword'), Keys.chord(Keys.ENTER))
	}

	/**
	 * Login sebagai KPPN
	 * */
	@Keyword
	def loginKPPN() {
		WebUI.setText(findTestObject('Admin/Login Page/txtUsername'), GlobalVariable.UsrKPPN)
		WebUI.setText(findTestObject('Admin/Login Page/txtPassword'), GlobalVariable.pswdKPPN)
		//WebUI.click(findTestObject('Admin/Login Page/btnMasuk'))
		WebUI.sendKeys(findTestObject('Admin/Login Page/txtPassword'), Keys.chord(Keys.ENTER))
	}


	@Keyword
	def getCurrentTime(){
		Date today = new Date()
		String todaysDate = today.format('YYYY-MM-dd')
		String nowTime = today.format('HH:mm')
		String datetime = todaysDate + ' ' + nowTime
		println (datetime)
		return datetime
	}
}