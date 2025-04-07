import "./App.css";
import {Link, Outlet} from "@tanstack/react-router";

function App() {

  return (
      <>
          <h1>Hello World!</h1>
          <nav style={{marginBottom: 30 }}>
              <Link to={"/"}>Domů</Link> | <Link to={"/users"}>Tabulka uživatelů</Link> | <Link to={"/register-user"}>Vytvořit nového uživatele</Link>
          </nav>
          <Outlet />
      </>

  )
}

export default App;
