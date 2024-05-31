import React from 'react';
import AddUser from './components/AddUser';
import CreateChest from './components/CreateChest';
import BuyChest from './components/BuyChest';
import OpenChest from './components/OpenChest';

function App() {
    return (
        <div>
            <h1>Frontend</h1>
            <AddUser />
            <CreateChest />
            <BuyChest />
            <OpenChest />
        </div>
    );
}

export default App;
