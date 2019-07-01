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
import com.kms.katalon.core.configuration.RunConfiguration
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import java.awt.Robot
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

public class folderPath {
	def browserName = DriverFactory.getWebDriver().getCapabilities().getBrowserName()
	def String proDir = RunConfiguration.getProjectDir()
	def String OperatingSystem = System.getProperty('os.name')
	/**
	 * Get Image folder path
	 */
	def imageDir() {
		if (OperatingSystem == 'Windows 10') {
			if (browserName == 'firefox') {
				def projectDir = proDir.replaceAll("/","\\\\")
				def imgDir = projectDir + "\\\\Images\\\\kuepukis.jpg"
				return imgDir
			}
			else if (browserName == 'chrome') {
				def imgDir = proDir + "\\Images\\kuepukis.jpg"
				return imgDir
			}
		}
		else {
			def imgDir = proDir + "/Images/kuepukis.jpg"
			return imgDir
		}
	}

	def imageDir2() {
		if (OperatingSystem == 'Windows 10') {
			if (browserName == 'firefox') {
				def projectDir = proDir.replaceAll("/","\\\\")
				def imgDir = projectDir + "\\\\Images\\\\Logo-Gas-Flame.png"
				return imgDir
			}
			else if (browserName == 'chrome') {
				def imgDir = proDir + "\\Images\\Logo-Gas-Flame.png"
				return imgDir
			}
		}
		else {
			def imgDir = proDir + "/Images/Logo-Gas-Flame.png"
			return imgDir
		}
	}

	//file
	def filesDir() {
		if (OperatingSystem == 'Windows 10') {
			if (browserName == 'firefox') {
				def projectDir = proDir.replaceAll("/","\\\\")
				def fileDir = projectDir + "\\\\Files\\\\notadinas.doc"
				return fileDir
			}
			else if (browserName == 'chrome') {
				def fileDir = proDir + "\\Files\\notadinas.doc"
				return fileDir
			}
		}
		else {
			def fileDir = proDir + "/Files/notadinas.doc"
			return fileDir
		}
	}

	/**
	 * Get Pif (txt) folder path
	 */
	/**
	 @Keyword
	 def txtDir() {
	 if (OperatingSystem == 'Windows 10') {
	 if (browserName == 'firefox') {
	 def projectDir = proDir.replaceAll("/","\\\\")
	 def pifDir = projectDir + "\\\\Images\\\\pifexample.txt"
	 return pifDir
	 }
	 else if (browserName == 'chrome') {
	 def pifDir = proDir + "\\Images\\pifexample.txt"
	 return pifDir
	 }
	 }
	 else {
	 def pifDir = proDir + "/Images/pifexample.txt"
	 return pifDir
	 }
	 }
	 */

	@Keyword
	def uploadFile(TestObject to, String filePath) {
		WebUI.click(to)
		WebUI.delay(3)
		StringSelection ss = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		WebUI.delay(3)
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
}