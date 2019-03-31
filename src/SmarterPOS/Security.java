package SmarterPOS;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Optional;
import java.util.Scanner;

public class Security {
	private static String secretKey = "harvest.hinet-sy.com-hinet-smart@solutions#";
	private static String salt = "harvest.hinet-sy.com-hinet-smart@solutions#";
	private String SNtobeDecoded = null;

	public Security() {
	}

	public String getSNtobeDecoded() {
		return SNtobeDecoded;
	}

	public void setSNtobeDecoded(String SNtobeDecoded) {
		this.SNtobeDecoded = SNtobeDecoded;
	}

	public Security(String SN) {
		this.SNtobeDecoded = SN;
	}

	/**
	 * Written By : Feras Alawadi
	 * Method to calculate serial Number.
	 *
	 * @return return serial Number as a String
	 * @throws IOException
	 * @throws InterruptedException
	 */

	private String editSerial(String Serial) throws IOException {
// Replace All Dashes (-)
		String Temp = Serial.replaceAll("-", "");
//        System.out.println("Temp: "+Temp);
		Temp = decrypt(Temp);
//        System.out.println("Decrypted String: "+Temp);
//  wQUR-fBCO-F2OO-fnx2-J9is-8g==

		// Reverse The String
		String unreverse = "";
		for (int i = Temp.length() - 1; i >= 0; i--) {
			unreverse = unreverse + Temp.charAt(i);
		}
		Serial = unreverse;
//        System.out.println("Unreversed String: "+Serial);
		String FinalSerial = Serial.substring(0, deviceID().length());
//        System.out.println("Final SN : "+FinalSerial);
		return FinalSerial;
	}

	public String decrypt(String EnteredSN) {

//        String FirstSN = EnteredSN;//.replaceAll("-", "");
//        System.out.println("removing Dashes : " + FirstSN);
		try {
			byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			return new String(cipher.doFinal(Base64.getDecoder().decode(EnteredSN)));
		} catch (Exception e) {

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error at registering the App");
			alert.setHeaderText("Activation is Wrong");
			alert.setContentText("Excuse Me! Make Sure Serial Number is Correct");
			alert.showAndWait();
//            System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	public boolean CheckSerial() throws IOException, InterruptedException, NoSuchAlgorithmException {
		EncryptSN encyptSN = new EncryptSN();

		String filePath = System.getProperty("user.dir");
		String FileName = filePath + "/Licence.lic";
//            System.out.println("Path:" +filePath);
		File file = new File(FileName);
		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(new File(FileName)))) {
				String line = "";
				Scanner fileReader = new Scanner(file, "UTF-8");
				fileReader.useDelimiter("\\Z"); // \Z means EOF.
				String out = fileReader.next();
				fileReader.close();
				String TempDevID = deviceID();
				TempDevID = encyptSN.EditSerialNumber(TempDevID);
				encyptSN.setDeviceSN(TempDevID);
				String Temp = encyptSN.getDeviceSN();
				if ((line = reader.readLine()) != null) {
					if (Temp.equals(line)) {
						System.out.println("licenced Version ");
						return true;
					} else {
						System.out.println("serial wrong");
						TextInputDialog dialog = new TextInputDialog();
						dialog.setTitle("Some Thing wrong with Licence File");
						dialog.setHeaderText("Hey! Please register you App," + "\n" + "send the Following ID to YafaTek Support at:" + "\n" + "info@hinet-sy.com " + "\n" + "or Visit http://hinet-sy.com For more info " + "\n" + " Cheers!");
						dialog.setContentText("Your Device ID: " + deviceID());
						// Traditional way to get the response value.
						Optional<String> result = dialog.showAndWait();
						if (result.isPresent()) {
							String enteredString = result.get();
//                            System.out.println("String is : " + enteredString);
							String SNresult = editSerial(enteredString);
							if (Compare(deviceID(), SNresult))
								SaveSerialFile(enteredString);
							return true;
						} else {
							Platform.exit();
							return false;
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		} else {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Register Smarter PointOfSale");
			dialog.setHeaderText("Hey! Please register you App," + "\n" + "send the Following ID to YafaTek Support at:" + "\n" + "info@hinet-sy.com " + "\n" + "or Visit http://hinet-sy.com For more info " + "\n" + " Cheers!");
			dialog.setContentText("Your Device ID: " + deviceID());
// Traditional way to get the response value.
			Optional<String> result = dialog.showAndWait();
			if (!result.get().isEmpty()) {
				String enteredString = result.get();
//                System.out.println("String is : " + enteredString);
				String SNresult = editSerial(enteredString);
				if (Compare(deviceID(), SNresult))
					SaveSerialFile(enteredString);
				return true;
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error at registering");
				alert.setHeaderText("Some Thing Wrong");
				alert.setContentText("There is Some Thing wrong With the Activation Key , Make Sure Key is correct");
				alert.showAndWait();
				Platform.exit();
				return false;
			}
		}
	}

	public String deviceID() throws IOException {
		if (osIsMac()) {
			ProcessBuilder pb = new ProcessBuilder("bash", "-c",
					"ioreg -l | awk '/IOPlatformSerialNumber/ { print $4;}'");
			pb.redirectErrorStream(true);
			String SN = "";
			try {
				Process p = pb.start();
				// read from the process's combined stdout & stderr
				BufferedReader stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
//        System.out.println("STD:"+stdout);
				while ((SN = stdout.readLine()) != null) {
					return SN;
				}
			} catch (IOException e) {
				e.getMessage();
			}
			return null;
		} else {
			String result = "";
			try {
				String drive = "c";
				File file = File.createTempFile("realhowto", ".vbs");
				file.deleteOnExit();
				FileWriter fw = new FileWriter(file);

				String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
						+ "Set colDrives = objFSO.Drives\n"
						+ "Set objDrive = colDrives.item(\"" + drive + "\")\n"
						+ "Wscript.Echo objDrive.SerialNumber";  // see note
				fw.write(vbs);
				fw.close();
				Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
				BufferedReader input =
						new BufferedReader
								(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = input.readLine()) != null) {
					result += line;
				}
				input.close();
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	private boolean Compare(String DeviceID, String SerialNumber) {
		if (SerialNumber.equals(DeviceID)) {
			return true;
		}
		return false;
	}

	Boolean SaveSerialFile(String code) throws IOException {
		String path = new File("").getAbsoluteFile() + "/Licence.lic";
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {

				bw.write(code);
				bw.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Registering the App Successfully");
			alert.setHeaderText("Congrats! , your app has been registered Successfully!" + "\n" + "Thank you For Chose SmarterPOS  ^_^ ");
			alert.showAndWait();
			return true;
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error At File");
			alert.setHeaderText("Error At Saving");
			alert.setContentText("Huh! there is an Error at saving the File");
			alert.showAndWait();
		}
		return false;
	}

	/**
	 * written by : Feras Alawadi
	 *
	 * @return true if it is mac , otherwise it is Windows.
	 */
	private static boolean osIsMac() {
		String osName = System.getProperty("os.name").toLowerCase();
		boolean isMacOs = osName.startsWith("mac os x");
		if (isMacOs) {
			System.out.println("Operating System is :" + osName.toUpperCase());
			return true;
		} else {
			System.out.println("Operating System is :" + osName.toUpperCase());
			return false;
		}
	}
}
