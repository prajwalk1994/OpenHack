import React, { Component } from 'react';
import Axios from 'axios';
import url from '../../config/config'
import { Link, Redirect } from "react-router-dom";

class searchHackathons extends Component {
    constructor(props) {
        super(props);
        this.state = {
            hackathonSearched: "Hello World",
            minteamSize: 0,
            maxteamSize: 0,
            minteamSizeList: 0,
            maxteamSizeList: 0,
            visibility: false,
            allHackathonsData: [],
            hackathonsList: ["Code bug","codera"],
            visibility: false,
            members: {},
            roles: {},
            hackathonId: 0,
            teamName: ""
        }
    }

    componentDidMount = () => {
        //get all Hackathons list
        Axios.get(url + "/hackathons")
            .then((response) => {
                console.log(response.data)
                this.setState({
                    allHackathonsData: response.data
                })
                var tempHack = []
                var minSizeList = []
                var maxSizeList = []
                // var tempOrgIds=[]
                for (let i of response.data) {
                    if (i.status != "\"No\"") {
                        tempHack.push(i.name)
                        minSizeList.push(i.minTeam)
                        maxSizeList.push(i.maxTeam)
                    }
                }
                this.setState({
                    hackathonsList: tempHack,
                    minteamSizeList: minSizeList,
                    maxteamSizeList: maxSizeList
                    // organizationsIds:tempOrgIds
                })
                console.log("state after setting hackathons", this.state)
            })
            .catch((err) => {
                if (err.response) {
                    console.log("err", err.response)
                } else {
                    alert("Something went wrong!")
                }
            })
    }

    searchHackathons = () => {

    }

    sendJoinRequest = async (e) => {
        e.preventDefault();
        if (this.state.members.length != this.state.roles.length) {
            alert("please select all roles")
        }
        else if (this.state.teamName == "") {
            alert("Enter Team Name")
        }
        else {
            var requestBody = {}
            console.log(Object.keys(this.state.members).length)
            for (let i = 0; i < Object.keys(this.state.members).length; i++) {
                requestBody[this.state.members["member" + (i + 1)]] = await this.state.roles["role" + (i + 1)]
                // console.log(this.state.members["member"+(i+1)])
                // console.log(this.state.roles["role"+(i+1)])
            }
            console.log(requestBody)
        }
        await Axios.post(url + `/team/${this.state.teamName}/${this.state.hackathonId}`, requestBody)
            .then(async (response) => {
                console.log(response.data)
                await Axios.post(url + `/hackathonteam/${this.state.hackathonId}/${response.data.id}`).then(async response => {
                    alert("Creation successful")
                    await this.setState({
                        redirectTo: <Redirect to="/profile" />
                    })
                })

            })
            .catch((err) => {
                if (err.response) {
                    console.log("err", err.response)
                } else {
                    alert("Something went wrong!")
                }
            })

    }

    handleChange = async (e) => {

        if (e.target.name.startsWith("role")) {
            await this.setState({
                roles: {
                    ...this.state.roles,
                    [e.target.name]: e.target.value
                }

            })
        } else if (e.target.name.startsWith("member")) {
            await this.setState({
                members: {
                    ...this.state.members,
                    [e.target.name]: e.target.value
                }

            })
        }
        else {
            await this.setState({
                [e.target.name]: e.target.value
            })
        }


        console.log(this.state)

    }

    joinHackathon = async (e, id) => {
        //send id of Hackathon selected to backend
        console.log(this.state)
        e.preventDefault();
        await this.setState({
            visibility: true,
            hackathonId: id,
            minteamSize: this.state.minteamSizeList[id - 1],
            maxteamSize: this.state.maxteamSizeList[id - 1]
        })
        console.log("hackathon id ", id, " min team size ", this.state.minteamSize, " max team size", this.state.maxteamSize)

    }
    DeleteHackathon = async (e, id) => {
        await Axios.post(url + `/hackathon/changeStatus/${id}/"No"`).then(res => {
            console.log(res.data)
        }).catch((err) => {
            if (err.response) {
                console.log("err", err.response)
            } else {
                alert("Something went wrong!")
            }
        })
    }
    changeTeamSize = () => {
        if (this.state.minteamSize + 1 <= this.state.maxteamSize) {
            this.setState({
                minteamSize: this.state.minteamSize + 1
            })
        } else {
            alert("max team limit reached")
        }
    }

    render() {

        var hackathonsListDiv = "";
        hackathonsListDiv = this.state.hackathonsList.map((item, index) => {
            return (
                <div className="row">
                    <div></div>
                    <h4 className="col-sm-6">{item}</h4>
                    {(localStorage.getItem("role") == "Admin") ? <button className="btn btn-danger col-sm-6" onClick={(e) => this.DeleteHackathon(e, (index + 1))}>Delete</button> : <button className="btn btn_login col-sm-6" onClick={(e) => this.joinHackathon(e, (index + 1))}>join</button>}
                    <br></br>
                </div>
            )
        })

        var searchedHackathonDiv = "";
        searchedHackathonDiv = (
            <div className="row justify-content-center">
                <label className="mb-4 mr-3" name="searchedName">{this.state.organizationSearched}</label>
                <button className="btn btn_login" onClick={this.sendJoinRequest}>join</button>
            </div>
        )

        var datalistHackathons = "";
        datalistHackathons = this.state.hackathonsList.map((item, index) => {
            return (
                <div>
                    <option value={item} />
                </div>
            )
        })


        var teamMembers = Array.apply(null, { length: this.state.minteamSize }).map((e, i) => (
            <tr>
                <td><input type="text" name={"member" + (i + 1)} placeholder={"team member " + (i + 1)} onChange={this.handleChange} ></input></td>
                <td>
                    <select class="form-control" name={"role" + (i + 1)} id="role" onChange={this.handleChange}>
                        <option selected>ProductManger</option>
                        <option>Engineer</option>
                        <option>FullStack</option>
                        <option>Designer</option>
                        <option>Other</option>
                    </select>
                </td>
            </tr>
        ));

        return (
            <div>
                <div>
                    {this.state.redirectTo}
                </div>
                <center>
                    <div></div>

                    <div className=" container  row">
                        <div className="justify-content-center formContainer">
                            <h3>Search Hackathons</h3>
                            <div className="">
                                <input list="hackathons1" type="text" className="form-control mb-4" name="searchHackathon" placeholder="Search Hackathons"></input>
                                <datalist id="hackathons1">
                                    {datalistHackathons}
                                </datalist>
                                <button className="btn btn_login" onClick={this.searchHackathons}>Search</button>
                            </div>
                        </div>
                        <div className="justify-content-center formContainer ml-5">
                            {!this.state.visibility ? <h3>List of all Hackathons</h3> : <div></div>}
                            <div className=" justify-content-center">
                                {!this.state.visibility ? hackathonsListDiv : searchedHackathonDiv}
                            </div>
                        </div>

                        <div >
                            {!this.state.visibility ? <div /> : <div className="justify-content-center formContainer ml-5">
                                <center>
                                    <h3>Enter Team Details</h3>
                                    <h4> <label className="mb-4 mr-3" name="searchedName">{this.state.hackathonSearched}</label></h4>
                                    <table>
                                        <tr>
                                            <td><input type="text" name="teamName" placeholder="Team Name" onChange={this.handleChange} />
                                            </td>
                                            <td>
                                            </td>
                                        </tr>
                                        {teamMembers}
                                    </table>
                                    <div className="row justify-content-center">
                                        <div className="col-sm-4">
                                            <button className="btn btn_login" onClick={this.changeTeamSize}>+ add More</button>
                                        </div>
                                    </div>
                                    <div className="row justify-content-center">
                                        <div className="col-sm-4 ">
                                            <button className="btn btn_login" onClick={this.sendJoinRequest}>join Hackathon</button>
                                        </div>
                                    </div>
                                </center>
                            </div>}
                        </div>

                    </div>
                </center>
            </div>
        );
    }
}

export default searchHackathons;