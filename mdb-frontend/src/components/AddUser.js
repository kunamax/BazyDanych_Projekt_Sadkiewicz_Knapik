import React, { useState } from 'react';

function AddUser() {
    const [user, setUser] = useState({
        name: '',
        surname: '',
        email: '',
        deposit: 0
    });
    const [message, setMessage] = useState('');
    const [isError, setIsError] = useState(false);

    const handleChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/users', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            });

            if (!response.ok) {
                throw new Error('Failed to add user');
            }

            setMessage('User added successfully!');
            setIsError(false);
        } catch (error) {
            console.error('Error adding user:', error);
            setMessage('Error adding user: ' + error.message);
            setIsError(true);
        }
    };

    return (
        <div>
            <h2>Add User</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    name="name"
                    placeholder="Name"
                    value={user.name}
                    onChange={handleChange}
                />
                <input
                    type="text"
                    name="surname"
                    placeholder="Surname"
                    value={user.surname}
                    onChange={handleChange}
                />
                <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={user.email}
                    onChange={handleChange}
                />
                <input
                    type="number"
                    name="deposit"
                    placeholder="Deposit"
                    value={user.deposit}
                    onChange={handleChange}
                />
                <button type="submit">Add User</button>
            </form>
            {message && <p className={isError ? 'error' : ''}>{message}</p>}
        </div>
    );
}

export default AddUser;
