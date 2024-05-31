import React, { useState } from 'react';

function OpenChest() {
    const [data, setData] = useState({
        userId: '',
        chestId: '',
        skinOpenedId: ''
    });
    const [message, setMessage] = useState('');
    const [isError, setIsError] = useState(false);

    const handleChange = (e) => {
        setData({ ...data, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/logs', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    type: 'CHEST_OPEN',
                    user_id: data.userId,
                    chest_id: data.chestId,
                    date: new Date().toISOString(),
                    details: {
                        skin_opened_id: data.skinOpenedId
                    }
                })
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(`Failed to open chest: ${errorData.message}`);
            }

            setMessage('Chest opened successfully!');
            setIsError(false);
        } catch (error) {
            console.error('Error opening chest:', error);
            setMessage('Error opening chest: ' + error.message);
            setIsError(true);
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
            {message && <p className={isError ? 'error' : ''}>{message}</p>}
        </div>
    );
}

export default OpenChest;
