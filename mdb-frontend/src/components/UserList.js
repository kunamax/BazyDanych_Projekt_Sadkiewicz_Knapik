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
                    <li key={user.id.chars.chars}>
                        <inf>{user.name.chars} {user.surname.chars} - {user.email.chars}</inf>
                        <inf>Deposit: {user.deposit.chars.chars}</inf>
                        <div>
                            <h4>Chests:</h4>
                            {user.chests && user.chests.length > 0 ? (
                                <ul>
                                    {user.chests.map(chest => (
                                        <li key={chest.chestId.chars.chars}>
                                            Chest ID: {chest.chestId.chars.chars} (Quantity: {chest.quantity.chars.chars})
                                        </li>
                                    ))}
                                </ul>
                            ) : (
                                <inf_no>No chests</inf_no>
                            )}
                        </div>
                        <div>
                            <h4>Skins:</h4>
                            {user.skins && user.skins.length > 0 ? (
                                <ul>
                                    {user.skins.map(skin => (
                                        <li key={skin.skinId.chars.chars}>
                                            Name: {skin.name.chars}, Type: {skin.type.chars}, Price: {skin.price.chars.chars}
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
