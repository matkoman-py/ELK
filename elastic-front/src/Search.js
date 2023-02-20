import {
  Button,
  Grid,
  List,
  ListItem,
  ListItemText,
  MenuItem,
  Radio,
  Select,
  TextField,
} from "@mui/material";
import { useState } from "react";
import axios from "axios";

const Search = () => {
  const [selectedValue, setSelectedValue] = useState("simple");
  const [field, setField] = useState("firstname");
  const [valueForSimple, setValueForSimple] = useState();

  const [operatorFirstname, setOperatorFirstname] = useState("not_included");
  const [operatorLastname, setOperatorLastname] = useState("not_included");
  const [operatorEducation, setOperatorEducation] = useState("not_included");
  const [operatorCv, setOperatorCv] = useState("not_included");
  const [operatorCoverLetter, setOperatorCoverLetter] =
    useState("not_included");

  const [advancedFirstname, setAdvancedFirstname] = useState("");
  const [advancedLastname, setAdvancedLastname] = useState("");
  const [advancedEducation, setAdvancedEducation] = useState("");
  const [advancedCv, setAdvancedCv] = useState("");
  const [advancedCoverLetter, setAdvancedCoverLetter] = useState("");

  const [city, setCity] = useState("Sombor");
  const [radius, setRadius] = useState(20);

  const [resultList, setResultList] = useState([]);

  const handleChange = (event) => {
    setSelectedValue(event.target.value);
  };

  const handleChangeSelect = (event) => {
    setField(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    console.log(selectedValue);
    if (selectedValue === "simple") {
      console.log("FIELD: " + field + "VALUE " + valueForSimple);

      axios
        .get(
          `http://localhost:8080/api/search/simple/${field}/${valueForSimple}`
        )
        .then((res) => setResultList(res.data));
    } else if (selectedValue === "advanced") {
      console.log(
        "OPERATOR: " +
          operatorFirstname +
          " VALUE FIRSTNAME" +
          advancedFirstname
      );
      console.log(
        "OPERATOR: " + operatorLastname + " VALUE Lastname" + advancedLastname
      );
      console.log(
        "OPERATOR: " +
          operatorEducation +
          " VALUE Education" +
          advancedEducation
      );
      console.log("OPERATOR: " + operatorCv + " VALUE Cv" + advancedCv);
      console.log(
        "OPERATOR: " +
          operatorCoverLetter +
          "VALUE CoverLetter" +
          advancedCoverLetter
      );
      var body = { query: [] };

      if (operatorCoverLetter !== "not_included") {
        body.query.push({
          field: "clContent",
          operator: operatorCoverLetter,
          value: advancedCoverLetter,
        });
      }

      if (operatorCv !== "not_included") {
        body.query.push({
          field: "cvContent",
          operator: operatorCv,
          value: advancedCv,
        });
      }

      if (operatorEducation !== "not_included") {
        body.query.push({
          field: "education",
          operator: operatorEducation,
          value: advancedEducation,
        });
      }

      if (operatorFirstname !== "not_included") {
        body.query.push({
          field: "firstname",
          operator: operatorFirstname,
          value: advancedFirstname,
        });
      }

      if (operatorLastname !== "not_included") {
        body.query.push({
          field: "lastname",
          operator: operatorLastname,
          value: advancedLastname,
        });
      }
      axios
        .post("http://localhost:8080/api/search/advanced", body)
        .then((res) => setResultList(res.data));
    } else if (selectedValue === "geo") {
      console.log("City: " + city + " Radius: " + radius);
      axios
        .post("http://localhost:8080/api/search/location", { city, radius })
        .then((res) => setResultList(res.data));
    }
  };

  return (
    <div>
      <h1>Search</h1>
      <Grid container spacing={2}>
        <Grid item container>
          <Radio
            checked={selectedValue === "simple"}
            onChange={handleChange}
            value="simple"
            name="radio-buttons"
            inputProps={{ "aria-label": "simple" }}
          />
          <h3>Simple query</h3>
          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={field}
            label="Field"
            onChange={handleChangeSelect}
          >
            <MenuItem value={"firstname"}>FirstName</MenuItem>
            <MenuItem value={"lastname"}>LastName</MenuItem>
            <MenuItem value={"education"}>Education</MenuItem>
            <MenuItem value={"cvContent"}>CV</MenuItem>
            <MenuItem value={"clContent"}>Cover letter</MenuItem>
          </Select>
          <TextField
            id="value"
            name="valueForSimple"
            label="Value"
            variant="outlined"
            value={valueForSimple}
            onChange={(event) => {
              setValueForSimple(event.target.value);
            }}
          />
        </Grid>
        <Grid item container justifyContent="flex-start">
          <Radio
            checked={selectedValue === "advanced"}
            onChange={handleChange}
            value="advanced"
            name="radio-buttons"
            inputProps={{ "aria-label": "advanced" }}
          />
          <h3>Advanced query</h3>

          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={operatorFirstname}
            label="Field"
            onChange={(event) => {
              setOperatorFirstname(event.target.value);
            }}
          >
            <MenuItem value={"and"}>AND</MenuItem>
            <MenuItem value={"or"}>OR</MenuItem>
            <MenuItem value={"not_included"}>DONT INCLUDE</MenuItem>
          </Select>
          <TextField
            label="FirstName"
            variant="outlined"
            value={advancedFirstname}
            onChange={(event) => {
              setAdvancedFirstname(event.target.value);
            }}
          />

          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={operatorLastname}
            label="Field"
            onChange={(event) => {
              setOperatorLastname(event.target.value);
            }}
          >
            <MenuItem value={"and"}>AND</MenuItem>
            <MenuItem value={"or"}>OR</MenuItem>
            <MenuItem value={"not_included"}>DONT INCLUDE</MenuItem>
          </Select>
          <TextField
            label="LastName"
            variant="outlined"
            value={advancedLastname}
            onChange={(event) => {
              setAdvancedLastname(event.target.value);
            }}
          />

          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={operatorEducation}
            label="Field"
            onChange={(event) => {
              setOperatorEducation(event.target.value);
            }}
          >
            <MenuItem value={"and"}>AND</MenuItem>
            <MenuItem value={"or"}>OR</MenuItem>
            <MenuItem value={"not_included"}>DONT INCLUDE</MenuItem>
          </Select>
          <TextField
            label="Education"
            variant="outlined"
            value={advancedEducation}
            onChange={(event) => {
              setAdvancedEducation(event.target.value);
            }}
          />

          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={operatorCv}
            label="Field"
            onChange={(event) => {
              setOperatorCv(event.target.value);
            }}
          >
            <MenuItem value={"and"}>AND</MenuItem>
            <MenuItem value={"or"}>OR</MenuItem>
            <MenuItem value={"not_included"}>DONT INCLUDE</MenuItem>
          </Select>
          <TextField
            label="Cv"
            variant="outlined"
            value={advancedCv}
            onChange={(event) => {
              setAdvancedCv(event.target.value);
            }}
          />

          <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={operatorCoverLetter}
            label="Field"
            onChange={(event) => {
              setOperatorCoverLetter(event.target.value);
            }}
          >
            <MenuItem value={"and"}>AND</MenuItem>
            <MenuItem value={"or"}>OR</MenuItem>
            <MenuItem value={"not_included"}>DONT INCLUDE</MenuItem>
          </Select>
          <TextField
            label="Cover letter"
            variant="outlined"
            value={advancedCoverLetter}
            onChange={(event) => {
              setAdvancedCoverLetter(event.target.value);
            }}
          />
        </Grid>
        <Grid item container justifyContent="flex-start">
          <Radio
            checked={selectedValue === "geo"}
            onChange={handleChange}
            value="geo"
            name="radio-buttons"
            inputProps={{ "aria-label": "geo" }}
          />
          <h3>Geo query</h3>

          <TextField
            label="City"
            variant="outlined"
            value={city}
            onChange={(event) => {
              setCity(event.target.value);
            }}
          />
          <TextField
            label="Radius"
            type="number"
            variant="outlined"
            value={radius}
            onChange={(event) => {
              setRadius(event.target.value);
            }}
          />
        </Grid>
        <Grid item container justifyContent="center">
          <Button variant="contained" color="success" onClick={handleSubmit}>
            SUBMIT
          </Button>
        </Grid>
        <Grid item container justifyContent="center">
          <List sx={{ width: "100%", bgcolor: "background.paper" }}>
            {resultList.map((item, index) => (
              <ListItem key={index} disableGutters divider>
                <ListItemText primary={`FirstName: ${item.firstName}`} />
                <ListItemText primary={`LastName: ${item.lastName}`} />
                <ListItemText primary={`Education: ${item.education}`} />
                <ListItemText primary={`Location: ${item.location}`} />
                <ListItemText primary={`CV: ${item.cv}`} />
                <ListItemText primary={`CL: ${item.coverLetter}`} />
              </ListItem>
            ))}
          </List>
        </Grid>
      </Grid>
    </div>
  );
};

export default Search;
