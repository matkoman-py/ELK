import * as React from "react";
import Box from "@mui/material/Box";
import { Button, Toolbar, AppBar } from "@mui/material";
import { useNavigate } from "react-router-dom";

export default function Navbar() {
  const navigate = useNavigate();
  function handleClickUpload() {
    navigate("");
  }

  function handleClickSearch() {
    navigate("/search");
  }

  function handleClickStatistics() {
    navigate("/statistics");
  }

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <Button color="inherit" onClick={handleClickUpload}>
            Upload
          </Button>
          <Button color="inherit" onClick={handleClickSearch}>
            Search
          </Button>
          <Button color="inherit" onClick={handleClickStatistics}>
            Statistics
          </Button>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
