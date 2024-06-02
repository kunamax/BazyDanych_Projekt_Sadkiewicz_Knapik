import React, { useState, useEffect } from 'react';

function UserList() {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/users/all');
                if (!response.ok) {
                    throw new Error('Failed to fetch users');
                }
                const data = await response.json();
                setUsers(data);
            } catch (error) {
                setError(error.message);
            } finally {
                setLoading(false);
            }
        };

        fetchUsers();
    }, []);

    if (loading) {
        return <p>Loading users...</p>;
    }

    if (error) {
        return <p className="error">{error}</p>;
    }

    return (
        <div>
            <h2>User List</h2>
            <ul>
                {users.map(user => (
                    <li key={user.id}>
                        <inf>{user.name} {user.surname} - {user.email}</inf>
                        <inf>Deposit: {user.deposit}</inf>
                        <div>
                            <h4>Chests:</h4>
                            {user.chests && user.chests.length > 0 ? (
                                <ul>
                                    {user.chests.map(chest => (
                                        <li key={chest.chestId.toString()}>
                                            Chest ID: {chest.chestId.toString()} (Quantity: {chest.quantity})
                                        </li>
                                    ))}
                                </ul>
                            ) : (
                                <info_no>No chests</info_no>
                            )}
                        </div>
                        <div>
                            <h4>Skins:</h4>
                            {user.skins && user.skins.length > 0 ? (
                                <ul>
                                    {user.skins.map(skin => (
                                        <li key={skin.id.toString()}>
                                            Name: {skin.name}, Type: {skin.type}, Price: {skin.price}
                                        </li>
                                    ))}
                                </ul>
                            ) : (
                                <inf_no>No skins</inf_no>
                            )}
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default UserList;
