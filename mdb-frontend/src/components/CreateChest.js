import React, { useState } from 'react';

function CreateChest() {
  const [user, setUser] = useState({
    name: '',
    price: 0
  });

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
        throw new Error('Failed to create chest');
      }

      alert('Chest created successfully!');
    } catch (error) {
      console.error('Error creating chest:', error);
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
          value={user.name}
          onChange={handleChange}
        />
        <input
          type="number"
          name="price"
          placeholder="Price"
          value={user.price}
          onChange={handleChange}
        />
        <button type="submit">Create Chest</button>
      </form>
    </div>
  );
}

export default CreateChest;