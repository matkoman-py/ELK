import { Button, Grid, TextField } from "@mui/material";
import { useState } from "react";
import axios from "axios";

const Upload = () => {
  const initialValues = {
    firstName: "Mateja",
    lastName: "Cosovic",
    email: "mat@cos.com",
    education: "HIGH_SCHOOL",
    address: "Petra Petrovica 15",
    phone: "+14884111",
    lat: 23.4,
    lon: 45.5,
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormValues({
      ...formValues,
      [name]: value,
    });
  };

  const [formValues, setFormValues] = useState(initialValues);
  const [cv, setCv] = useState();
  const [coverLetter, setCoverLetter] = useState();

  const handleCvChange = (e) => {
    if (e.target.files) {
      setCv(e.target.files[0]);
    }
  };

  const handleCoverLetterChange = (e) => {
    if (e.target.files) {
      setCoverLetter(e.target.files[0]);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log(formValues);

    let formData = new FormData();
    formData.append("cv", cv);
    formData.append("coverLetter", coverLetter);

    formData.append("firstName", formValues.firstName);
    formData.append("lastName", formValues.lastName);
    formData.append("email", formValues.email);
    formData.append("education", formValues.education);
    formData.append("address", formValues.address);
    formData.append("phone", formValues.phone);
    formData.append("lat", formValues.lat * 1);
    formData.append("lon", formValues.lon * 1);

    console.log(formData);
    axios.post("http://localhost:8080/api/storage", formData, {
      headers: {
        "content-type": "multipart/form-data",
      },
    });
  };

  return (
    <div>
      <h1>Submit application</h1>
      <Grid
        container
        direction="column"
        justifyContent="space-between"
        alignItems="center"
        spacing={2}
      >
        <TextField
          id="firstName"
          name="firstName"
          label="FirstName"
          variant="outlined"
          onChange={handleInputChange}
          value={formValues.firstName}
        />
        <br></br>

        <TextField
          id="lastName"
          name="lastName"
          label="LastName"
          variant="outlined"
          onChange={handleInputChange}
          value={formValues.lastName}
        />
        <br></br>

        <TextField
          id="email"
          name="email"
          label="Email"
          variant="outlined"
          onChange={handleInputChange}
          value={formValues.email}
        />
        <br></br>

        <TextField
          id="education"
          name="education"
          label="Education"
          variant="outlined"
          onChange={handleInputChange}
          value={formValues.education}
        />
        <br></br>

        <TextField
          id="address"
          name="address"
          label="Address"
          variant="outlined"
          onChange={handleInputChange}
          value={formValues.address}
        />
        <br></br>

        <TextField
          id="phone"
          name="phone"
          label="Phone"
          variant="outlined"
          onChange={handleInputChange}
          value={formValues.phone}
        />
        <br></br>

        <TextField
          id="latitude"
          name="lat"
          label="Latitude"
          variant="outlined"
          type="number"
          value={formValues.lat}
          onChange={handleInputChange}
        />
        <br></br>
        <TextField
          id="longitude"
          name="lon"
          label="Longitude"
          variant="outlined"
          onChange={handleInputChange}
          type="number"
          value={formValues.lon}
        />
        <br></br>

        <Button variant="contained">
          Upload CV
          <input type="file" accept=".pdf" onChange={handleCvChange} />
        </Button>
        <br></br>

        <Button variant="contained">
          Upload CL
          <input type="file" accept=".pdf" onChange={handleCoverLetterChange} />
        </Button>
        <br></br>

        <Button variant="contained" color="success" onClick={handleSubmit}>
          SUBMIT
        </Button>
      </Grid>
    </div>
  );
};

export default Upload;
