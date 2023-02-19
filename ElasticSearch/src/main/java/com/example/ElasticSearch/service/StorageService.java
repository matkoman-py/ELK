package com.example.ElasticSearch.service;

import com.example.ElasticSearch.dto.ApplicationDTO;
import com.example.ElasticSearch.model.Applicant;
import com.example.ElasticSearch.repository.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final ApplicantRepository applicantRepository;

    public void handleApplication(ApplicationDTO applicationDTO) {
        System.out.println(applicationDTO);

        MultipartFile cv = applicationDTO.getCv();
        MultipartFile coverLetter = applicationDTO.getCoverLetter();

        Applicant applicant = new Applicant();
        applicant.setFirstname(applicationDTO.getFirstName());
        applicant.setLastname(applicationDTO.getLastName());
        applicant.setAddress(applicationDTO.getAddress());
        applicant.setPhone(applicationDTO.getPhone());
        applicant.setEmail(applicationDTO.getEmail());
        applicant.setLocation(new GeoPoint(applicationDTO.getLat(), applicationDTO.getLon()));
        applicant.setEducation(applicationDTO.getEducation());

        applicantRepository.save(applicant);

        String cvName = applicant.getId()+"-CV";
        String clName = applicant.getId()+"-CL";

        try {
            cvName = saveUploadedFile(cv, cvName);
            clName = saveUploadedFile(coverLetter, clName);
        } catch(Exception e) {
            e.printStackTrace();
        }

        applicant.setCvContent(getContent(new File(cvName)));

        applicant.setClContent(getContent(new File(clName)));

        applicantRepository.save(applicant);
    }

    private String saveUploadedFile(MultipartFile file, String fileName) throws IOException {
        String retVal = null;
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            //sa ovom dobijas file
            Path path = Paths.get("src/main/resources/static/" +
                      fileName);

            Files.write(path, bytes);
            retVal = path.toString();
        }
        return retVal;
    }


    public String getContent(File file) {
        String retVal="";
        try {

            PDDocument pdf = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            retVal = pdfStripper.getText(pdf);
            pdf.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Greksa pri konvertovanju dokumenta u pdf");
        }
        return retVal;
    }

}
