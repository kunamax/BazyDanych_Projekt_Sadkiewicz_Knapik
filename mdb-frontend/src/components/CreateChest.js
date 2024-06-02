import React, { useState } from 'react';

function CreateChest() {
    const [chest, setChest] = useState({
        name: '',
        price: 0,
        skins: [{ name: '', type: '', rarity: '', odds: 0, basePrice: 0 }]
    });
    const [message, setMessage] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setChest({ ...chest, [name]: value });
    };

    const handleSkinChange = (index, e) => {
        const { name, value } = e.target;
        const updatedSkins = chest.skins.map((skin, i) =>
            i === index ? { ...skin, [name]: value } : skin
        );
        setChest({ ...chest, skins: updatedSkins });
    };

    const addSkinField = () => {
        setChest({ ...chest, skins: [...chest.skins, { name: '', type: '', rarity: '', odds: 0, basePrice: 0 }] });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/chests', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(chest)
            });

            if (!response.ok) {
                throw new Error('Failed to create chest');
            }

            setMessage('Chest created successfully!');
        } catch (error) {
            console.error('Error creating chest:', error);
            setMessage('Error creating chest');
        }
    };

    return (
        <div>
            <h2>Create Chest</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="name"
                    placeholder="Name"
                    value={chest.name}
                    onChange={handleChange}
                />
                <input
                    type="number"
                    name="price"
                    placeholder="Price"
                    value={chest.price}
                    onChange={handleChange}
                />
                {chest.skins.map((skin, index) => (
                    <div key={index}>
                        <input
                            type="text"
                            name="name"
                            placeholder="Skin Name"
                            value={skin.name}
                            onChange={(e) => handleSkinChange(index, e)}
                        />
                        <input
                            type="text"
                            name="type"
                            placeholder="Skin Type"
                            value={skin.type}
                            onChange={(e) => handleSkinChange(index, e)}
                        />
                        <input
                            type="text"
                            name="rarity"
                            placeholder="Skin Rarity"
                            value={skin.rarity}
                            onChange={(e) => handleSkinChange(index, e)}
                        />
                        <input
                            type="number"
                            name="odds"
                            placeholder="Skin Odds"
                            value={skin.odds}
                            onChange={(e) => handleSkinChange(index, e)}
                        />
                        <input
                            type="number"
                            name="basePrice"
                            placeholder="Skin Base Price"
                            value={skin.basePrice}
                            onChange={(e) => handleSkinChange(index, e)}
                        />
                    </div>
                ))}
                <button type="button" onClick={addSkinField}>Add Another Skin</button>
                <button type="submit">Create Chest</button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );
}

export default CreateChest;
