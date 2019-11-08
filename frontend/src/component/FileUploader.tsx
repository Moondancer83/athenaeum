import * as React from "react";

interface Props {
  onChange(event: any): void;
}

export default function FileUploader(props: Props) {
  return <input type={"file"} name={"file"} onChange={props.onChange} />;
}
