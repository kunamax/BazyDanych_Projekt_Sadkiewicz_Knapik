import React, { useEffect, useState } from 'react';

function ChestList() {
    const [chests, setChests] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch('/api/chests')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => setChests(data))
            .catch(error => setError(error.message));
    }, []);

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div>
            <h2>Chests</h2>
            <ul>
                {chests.map(chest => (
                    <li key={chest._id}>
                        {chest.name} - Price: {chest.price}
                        <ul>
                            {chest.skins.map(skin => (
                                <li key={skin._id}>
                                    {skin.name} ({skin.rarity}) - Odds: {skin.odds}
                                </li>
                            ))}
                        </ul>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default ChestList;
