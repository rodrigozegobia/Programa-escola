import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import List from './pages/user/List';
import Include from './pages/user/Include';
import MyRoutes from './routes/MyRoutes';
import Dashboard from './pages/dashboard/Dashboard';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<MyRoutes/>}>
          <Route path='/user/list' element={<List/>}/>
          <Route path='/user/save' element={<Include/>}/>
          <Route path='/dashboard' element={<Dashboard/>}/>
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
