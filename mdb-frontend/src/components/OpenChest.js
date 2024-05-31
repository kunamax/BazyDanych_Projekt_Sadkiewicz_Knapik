import React, { useState } from 'react';

function OpenChest() {
    const [data, setData] = useState({
        userId: '',
        chestId: '',
        skinOpenedId: ''
    });
    const [message, setMessage] = useState('');

    const handleChange = (e) => {
        setData({ ...data, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/users/open-chest', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({
                    userId: data.userId,
                    chestId: data.chestId,
                    skinId: data.skinOpenedId
                })
            });

            if (!response.ok) {
                throw new Error('Failed to open chest');
            }

            setMessage('Chest opened successfully!');
        } catch (error) {
            console.error('Error opening chest:', error);
            setMessage('Error opening chest');
        }
    };

    return (
        <div>
            <h2>Open Chest</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="userId"
                    placeholder="User ID"
                    value={data.userId}
                    onChange={handleChange}
                />
                <input
                    type="text"
                    name="chestId"
                    placeholder="Chest ID"
                    value={data.chestId}
                    onChange={handleChange}
                />
                <input
                    type="text"
                    name="skinOpenedId"
                    placeholder="Skin Opened ID"
                    value={data.skinOpenedId}
                    onChange={handleChange}
                />
                <button type="submit">Open Chest</button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );
}

export default OpenChest;
