import { useState } from 'react';

export default function Player({ initialName, symbol }) {
    const [ playerName, setPlayerName ] = useState(initialName);
    const [ isEditing, setIsEditing ] = useState(false);
    
    function handleEditClick() { 
        // 이전 상태값에 기반하여 변경하는 경우 상태 업데이트 함수로 새로운 함수를 보내야 한다.   
        // 즉각적인 상태 변경이 되지 않고 스케줄링한다.             
        //setIsEditing(!isEditing);
        // () 함수를 리액트가 호출하여 자동으로 현재 상태값을 가지게 된다. 가장 최신버전의 상태
        setIsEditing((editing) => {
            console.log(editing);
            return !editing;
        });
    }

    function handleChange(event) {
        setPlayerName(event.target.value);
    }

    let editablePlayerName = <span className="player-name">{playerName}</span>;

    if (isEditing) {
        editablePlayerName = (
            <input type="text" required value={playerName} onChange={handleChange} />
        );
    }


    return (
        <li>
            <span className="palyer">
                {editablePlayerName}                
                <span className='player-symbol'>{symbol}</span>
            </span>
            <button onClick={handleEditClick}>{isEditing ? 'Save' : 'Edit'}</button>
        </li>
    );
}