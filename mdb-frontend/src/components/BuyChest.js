import React, { useState } from 'react';

function BuyChest() {
    const [data, setData] = useState({
        userId: '',
        chestId: '',
        quantity: 1
    });
    const [message, setMessage] = useState('');
    const [isError, setIsError] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setData({ ...data, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch(`http://localhost:8080/api/users/${data.userId}/chests`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    chestId: data.chestId,
                    quantity: data.quantity
                })
            });

            if (!response.ok) {
                throw new Error('Failed to buy chest');
            }

            setMessage('Chest purchased successfully!');
            setIsError(false);
        } catch (error) {
            console.error('Error buying chest:', error);
            setMessage('Error buying chest: ' + error.message);
            setIsError(true);
        }
    };

    return (
        <div>
            <h2>Buy Chest</h2>
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
                    type="number"
                    name="quantity"
                    placeholder="Quantity"
                    value={data.quantity}
                    onChange={handleChange}
                />
                <button type="submit">Buy Chest</button>
            </form>
            {message && <p className={isError ? 'error' : ''}>{message}</p>}
        </div>
    );
}

export default BuyChest;
