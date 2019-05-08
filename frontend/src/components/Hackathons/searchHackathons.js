import React, { Component } from 'react';

class searchHackathons extends Component {
    constructor(props) {
        super(props);
        this.state = {
            hackathonSearched: "Hello World",
            minteamSize: 0,
            maxteamSize:0,
            visibility: false
        }
    }

    componentDidMount = () => {
        //get all Hackathons list
    }

    searchHackathons = () => {
        
    }

    sendJoinRequest = () => {
        //send id of Hackathon selected to backend
    }

    changeTeamSize = ()=>{
        if(this.state.minteamSize+1<=this.state.maxteamSize){
            this.setState({
                minteamSize:this.state.minteamSize+1
            })
        }else{
            alert("max team limit reached")
        }
    }

    render() {

        var teamMembers = Array.apply(null, { length: this.state.minteamSize }).map((e, i) => (
            <tr>
                <td><input type="text" name={"member" + (i + 1)} placeholder={"team member " + (i + 1)} ></input></td>
                <td>
                    <select class="form-control" id="role">
                        <option>ProductManger</option>
                        <option>Engineer</option>
                        <option>Full Stack</option>
                        <option>Designer</option>
                        <option>Other</option>
                    </select>
                </td>
            </tr>
        ));

        return (
            <div>
                <h3>Search Hackathons</h3>
                <div>
                    <div className="row justify-content-center">
                        <div className="col-sm-4 ">
                            <input type="text" className="form-control mb-4" name="searchHackathon" placeholder="Search Hackathons"></input>
                            <button className="btn btn-primary" onClick={this.searchHackathons}>Search</button>                            </div>
                    </div>

                    {/* MODAL FOR TEAM START */}
                    
                    {/* MODAL FOR TEAM END */}
                    { !this.state.visibility ? <div/> : <div>
                <center>
                    <h3>Enter Team Details</h3>
                   <h4> <label className="mb-4 mr-3" name="searchedName">{this.state.hackathonSearched}</label></h4>
                    <table>
                        <tr>
                            <td><input type="text" name="teamName" placeholder="Team Name" />
                            </td>
                            <td>
                            </td>
                        </tr>
                        {teamMembers}
                    </table>
                    <div className="row justify-content-center">
                        <div className="col-sm-4 ">
                            <button className="btn btn-primary" onClick={this.changeTeamSize}>+ add More</button>
                        </div>
                    </div>
                    <div className="row justify-content-center">
                        <div className="col-sm-4 ">
                            <button className="btn btn-primary" onClick={this.sendJoinRequest}>join Hackathon</button>
                        </div>
                    </div>
                </center>
            </div> }
                    


                </div>
            </div>
        );
    }
}

export default searchHackathons;