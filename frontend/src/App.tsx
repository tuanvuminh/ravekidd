import "./styles/App.css";
import { LoginPage } from "./pages/loginPage";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import { AmbientRoom } from "./pages/ambient";
import { DarkRoom } from "./pages/dark";
import { MainPage } from "./pages/mainPage";
import { Profile } from "./pages/profile";
import { NavigationBar } from "./components/navigationBar";
import { ApolloClient, InMemoryCache, ApolloProvider } from "@apollo/client";
import { useEffect, useState } from "react";
import axios from "axios";

function App() {
  const client = new ApolloClient({
    cache: new InMemoryCache(),
    uri: "http://localhost:8080/graphql",
  });
  const [authenticated, setAuthenticated] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      const bearerToken = `Bearer ${token}`;

      axios
        .post(
          "http://localhost:8080/validate",
          {},
          {
            headers: {
              Authorization: bearerToken,
            },
          }
        )
        .then((response) => {
          if (response.status === 200) {
            console.log("Token is valid");
            setAuthenticated(true);
          } else {
            setAuthenticated(false);
          }
        })
        .catch((error) => {
          setAuthenticated(false);
        });
    } else {
      setAuthenticated(false);
    }
  }, []);

  const handleLogout = () => {
    setAuthenticated(false);
  };

  return (
    <ApolloProvider client={client}>
      <div className="App">
        <Router>
          {authenticated && <NavigationBar onLogout={handleLogout} />}

          <Routes>
            <Route
              path="/"
              element={authenticated ? <Navigate to="/main" /> : <LoginPage />}
             />

            <Route
              path="/ambient"
              element={authenticated ? <AmbientRoom /> : <Navigate to="/" />}
            />

            <Route
              path="/main"
              element={authenticated ? <MainPage /> : <Navigate to="/" />}
            />

            <Route
              path="/dark"
              element={authenticated ? <DarkRoom /> : <Navigate to="/" />}
            />

            <Route
              path="/profile"
              element={authenticated ? <Profile /> : <Navigate to="/" />}
            />
          </Routes>
        </Router>
      </div>
    </ApolloProvider>
  );
}

export default App;
