import { useState } from 'react';



export default function Player({ name, symbol }) {
    const [ isEditing, setIsEditing ] = useState(false);

    function handleEditClick() { 
        // 이전 상태값에 기반하여 변경하는 경우 상태 업데이트 함수로 새로운 함수를 보내야 한다.   
        // 즉각적인 상태 변경이 되지 않고 스케줄링한다.             
        //setIsEditing(!isEditing);
        // () 함수를 리액트가 호출하여 자동으로 현재 상태값을 가지게 된다. 가장 최신버전의 상태
        setIsEditing((editing) => !isEditing);
    }

    let playerName = <span className='player-name'>{name}</span>;

    if (isEditing) {
        playerName = <input type="text" required value={name} />;
    }


    return (
        <li>
            <span className="palyer">
                {playerName}                
                <span className='player-symbol'>{symbol}</span>
            </span>
            <button onClick={handleEditClick}>{isEditing ? 'Save' : 'Edit'}</button>
        </li>
    );
}