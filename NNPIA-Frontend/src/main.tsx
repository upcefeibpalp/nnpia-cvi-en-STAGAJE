import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import theme from './theme.ts';
import {ThemeProvider} from "@mui/material";
import {RouterProvider} from "@tanstack/react-router";
import { router } from "./routes.tsx";

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <ThemeProvider theme={theme}>
          <RouterProvider router={router}/>
      </ThemeProvider>
  </StrictMode>,
)
