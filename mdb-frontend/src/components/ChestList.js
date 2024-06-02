import React, { useState, useEffect } from 'react';

function ChestList() {
    const [chests, setChests] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchChests = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/chests/all');
                if (!response.ok) {
                    throw new Error('Failed to fetch chests');
                }
                const data = await response.json();
                setChests(data);
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        fetchChests();
    }, []);

    if (loading) {
        return <p>Loading chests...</p>;
    }

    if (error) {
        return <p className="error">{error}</p>;
    }

    return (
        <div>
            <h2>Chest List</h2>
            <ul>
                {chests.map(chest => (
                    <li key={chest.id.chars.chars}>
                        <inf>{chest.name.chars} - Price: {chest.price.chars}</inf>
                        <div>
                            <h4>Skins:</h4>
                            {chest.skins && chest.skins.length > 0 ? (
                                <ul>
                                    {chest.skins.map(skin => (
                                        <li key={skin.name.chars}>
                                            Name: {skin.name.chars}, Rarity: {skin.rarity.chars}, Odds: {skin.odds.chars.chars}
                                        </li>
                                    ))}
                                </ul>
                            ) : (
                                <p>No skins</p>
                            )}
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default ChestList;
