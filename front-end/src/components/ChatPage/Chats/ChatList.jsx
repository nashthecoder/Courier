import React from 'react';
import ChatPicker from './ChatPicker';
import SearchBar from '../Search/SearchBar'

function ChatList(props) {

    const onCreateChatClicked = () => {
        props.createChat();
    }

    return (
        <div className="col-3 border pt-2 pl-0 pr-0 mh-100">
            <div className="d-flex flex-column h-100 mh-100">
                <div className="text-center">
                    <h1 className="">Chats</h1>
                    <div className="row pb-2 justify-content-center">
                        <SearchBar/>
                        <button onClick={onCreateChatClicked} className="btn rounded-circle ml-1 mr-1 pl-2 pr-2"><i className="fa fa-plus"/></button>
                    </div>
                </div>
                <div className="col mh-100 h-100 flex-grow-1 overflow-auto p-0">
                        <ul className="list-group border-top">
                            {[...props.chats.values()].map((chat) => (
                                <ChatPicker changeCurrentChat={props.changeCurrentChat} 
                                            setChatName={props.setChatName}
                                            isCurrentChat={chat.id === props.currentChat.id} 
                                            id={chat.id} 
                                            name={chat.name} 
                                            key={chat.id}
                                            created={chat.created}/>
                            ))}
                        </ul>
                </div>
            </div>
        </div>
    );
}

export default ChatList;
