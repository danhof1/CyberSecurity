
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequestInitializer;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.*;
import java.time.LocalDateTime;
import java.nio.file.spi.FileTypeDetector;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/* class to demonstrate use of Drive files list API */
public class DriveQuickstart {
  /**
   * Application name.
   */
  private static final String APPLICATION_NAME = "RAT Trap";
  /**
   * Global instance of the JSON factory.
   */
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
  /**
   * Directory to store authorization tokens for this application.
   */
  private static final String TOKENS_DIRECTORY_PATH = "tokens";

  /**
   * Global instance of the scopes required by this quickstart.
   * If modifying these scopes, delete your previously saved tokens/ folder.
   */
  private static final List<String> SCOPES =
      Collections.singletonList(DriveScopes.DRIVE);
  private static final String CREDENTIALS_FILE_PATH = "/client_secret_493068561708-0qcjidt6eel6lk87hqehjk9dbnqe9pg4.apps.googleusercontent.com.json";

  /**
   * Creates an authorized Credential object.
   *
   * @param HTTP_TRANSPORT The network HTTP Transport.
   * @return An authorized Credential object.
   * @throws IOException If the credentials.json file cannot be found.
   */
  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
      throws IOException {
    // Load client secrets.
    InputStream in = DriveQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
    if (in == null) {
      throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
    }
    GoogleClientSecrets clientSecrets =
        GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

    // Build flow and trigger user authorization request.
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
        .setAccessType("offline")
        .build();
    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
    Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    //returns an authorized Credential object.
    return credential;
  }


  public static void main(String[] args) throws IOException, GeneralSecurityException {
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
            .setApplicationName(APPLICATION_NAME)
            .build();

    // List files
    listFiles(service);

   String existingFileId = getFileIdByName(service, "johnpork.jpeg");
   uploadFile(service, "/Users/kevinrosa/Desktop/johnpork.jpeg");
   LocalDateTime lastUploadTime = LocalDateTime.now();
   //String existingFileId = getFileIdByName(service, "johnpork.jpeg");

   while (true) {
    LocalDateTime currentTime = LocalDateTime.now();

    // Check if 24 hours have passed since the last upload
    if (currentTime.isAfter(lastUploadTime.plusHours(24))) {
        // Check if the file already exists
        //String existingFileId = getFileIdByName(service, "johnpork.jpeg");

        // If the file exists, update it; otherwise, upload a new file
        if (existingFileId != null) {
            // Update the existing file
            updateFile(service, existingFileId, "/Users/kevinrosa/Desktop/johnpork.jpeg");
            System.out.println("Updated File ID: " + existingFileId);
        } else {
            // Upload a new file
            uploadFile(service, "/Users/kevinrosa/Desktop/johnpork.jpeg");
        }

        // Update the last upload time
        lastUploadTime = LocalDateTime.now();
    }

    // Wait for a minute before checking again
    try {
        Thread.sleep(1000); // Sleep for 1 second in milliseconds
    } catch (InterruptedException e) {
        e.printStackTrace();
        break;
    }
}


    // Upload a file
    //uploadFile(service, "testing.txt", "text/plain");

    // Download a file
    downloadFile(service, "1yPBY_Ghk6HJQQlrYJ6mnwZ9utZg4JSyy", "/Users/kevinrosa/Desktop/demo/downloadtest.pdf");
  }

  //refactored method to listFiles
  private static void listFiles(Drive service) throws IOException {
    FileList result = service.files().list()
            .setPageSize(100)
            .setFields("nextPageToken, files(id, name)")
            .execute();
    List<File> files = result.getFiles();
    if (files == null || files.isEmpty()) {
        System.out.println("No files found.");
    } else {
        System.out.println("Files:");
        for (File file : files) {
            System.out.printf("%s (%s)\n", file.getName(), file.getId());
        }
    }
  }

  private static String getFileIdByName(Drive service, String fileName) throws IOException {
    FileList result = service.files().list()
            .setQ("name='" + fileName + "'")
            .setFields("files(id)")
            .execute();
    
    List<File> files = result.getFiles();
    if (files != null && !files.isEmpty()) {
        return files.get(0).getId();
    }
    return null; // Return null if the file is not found
}

  // Refactored method to upload a file
  private static void uploadFile(Drive service, String filePath) throws IOException {
    java.io.File file = new java.io.File(filePath);
    String fileName = file.getName(); // Extracting the file name without the path

    File fileMetadata = new File();
    fileMetadata.setName(fileName);

    Path path = Paths.get(filePath);
    FileContent mediaContent = new FileContent(Files.probeContentType(path), file);

    File uploadedFile = service.files().create(fileMetadata, mediaContent)
            .setFields("id")
            .execute();

    System.out.println("Uploaded File ID: " + uploadedFile.getId());
}

private static void updateFile(Drive service, String fileId, String filePath) throws IOException {
  java.io.File file = new java.io.File(filePath);

  FileContent mediaContent = new FileContent(Files.probeContentType(file.toPath()), file);

  File updatedFile = service.files().update(fileId, null, mediaContent)
          .setFields("id")
          .execute();

  System.out.println("Updated File ID: " + updatedFile.getId());
}

  // Refactored method to download a file
  private static void downloadFile(Drive service, String fileId, String destinationFilePath) {
    try {
        OutputStream outputStream = new FileOutputStream(destinationFilePath);
        service.files().get(fileId).executeMediaAndDownloadTo(outputStream);
        System.out.println("File downloaded successfully to: " + destinationFilePath);
    } catch (IOException errorcode) {
        System.err.println("Error downloading the file: " + errorcode.getMessage());
        errorcode.printStackTrace();
    }
  }
}
// [END drive_quickstart]
//
