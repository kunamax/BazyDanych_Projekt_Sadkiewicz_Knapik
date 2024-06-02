import React, { useState } from 'react';
import AddUser from './components/AddUser';
import CreateChest from './components/CreateChest';
import BuyChest from './components/BuyChest';
import OpenChest from './components/OpenChest';
import UserList from './components/UserList';
import ChestList from './components/ChestList';
import LogList from './components/LogList';

function App() {
    const [currentPage, setCurrentPage] = useState('home');

    const renderPage = () => {
        switch (currentPage) {
            case 'users':
                return <UserList />;
            case 'chests':
                return <ChestList />;
            case 'logs':
                return <LogList />;
            default:
                return (
                    <div>
                        <AddUser />
                        <CreateChest />
                        <BuyChest />
                        <OpenChest />
                    </div>
                );
        }
    };

    return (
        <div>
            <h1>Frontend</h1>
            <nav>
                <button onClick={() => setCurrentPage('home')}>Home</button>
                <button onClick={() => setCurrentPage('users')}>User List</button>
                <button onClick={() => setCurrentPage('chests')}>Chest List</button>
                <button onClick={() => setCurrentPage('logs')}>Log List</button>
            </nav>
            {renderPage()}
        </div>
    );
}

export default App;
